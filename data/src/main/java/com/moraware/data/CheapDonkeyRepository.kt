package com.moraware.data

import com.moraware.data.base.WebServiceException
import com.moraware.data.interactors.Callback
import com.moraware.data.mappers.AddressMapper
import com.moraware.data.mappers.RideMapper
import com.moraware.data.models.SearchLocationRequest
import com.moraware.data.models.SearchLocationResponse
import com.moraware.data.models.SearchRidesRequest
import com.moraware.data.models.SearchRidesResponse
import com.moraware.data.webservices.google.GooglePlacesService
import com.moraware.data.webservices.lyft.LyftService
import com.moraware.data.webservices.uber.UberService
import com.moraware.data.models.ApplicationServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheapDonkeyRepository: IDataRepository {

    private var mRepositoryScheduler = RepositoryScheduler()
    private var mEnableDebugLogging = false
    private lateinit var mSettings: ApplicationServices

    lateinit var client: OkHttpClient
    lateinit var placesService: GooglePlacesService
    lateinit var lyftService: LyftService
    lateinit var uberService: UberService

    override fun initializeServices(settings: ApplicationServices?, debug: Boolean) {
        settings?.let {
            mSettings = settings
            mEnableDebugLogging = debug
            initWebServices()
        }
    }

    private fun initWebServices() {
        client = OkHttpClient().newBuilder()
                .addInterceptor(okhttp3.logging.HttpLoggingInterceptor().apply {
                    level = if (mEnableDebugLogging) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()

        val googleRetrofit = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        placesService = googleRetrofit.create(GooglePlacesService::class.java)

        mSettings.taxiServices.forEach {
            if("Lyft" == it.name){
                val lyftRetrofit = Retrofit.Builder()
                        .baseUrl(it.apiUrl)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                lyftService = lyftRetrofit.create(LyftService::class.java)
            }

            if("Uber" == it.name){
                val uberRetrofit = Retrofit.Builder()
                        .baseUrl(it.apiUrl)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                uberService = uberRetrofit.create(UberService::class.java)
            }
        }
    }

    override fun searchLocation(request: SearchLocationRequest, callback: Callback<SearchLocationResponse>) {
        mRepositoryScheduler.execute(Runnable {
            val call = placesService.searchLocation(mSettings.locationAPIKey, request.address)
            val destinations = call.execute().body()
            val domainDestination: SearchLocationResponse? = AddressMapper().transform(destinations)

            if(domainDestination != null) callback.onSuccess(domainDestination) else callback.onFailure(WebServiceException(null))
        })
    }

    override fun searchLocationSync(request: SearchLocationRequest): SearchLocationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchRides(request: SearchRidesRequest, callback: Callback<SearchRidesResponse>) {
        mRepositoryScheduler.execute(Runnable {
            val lyftCall = lyftService.getRideEstimate(request.destinationLat.toFloat(), request.destinationLng.toFloat(), request.currentLat.toFloat(), request.currentLng.toFloat())
            val costEstimates = lyftCall.execute().body()
            val uberCall = uberService.getRideEstimate(request.destinationLat.toFloat(), request.destinationLng.toFloat(), request.currentLat.toFloat(), request.currentLng.toFloat())
            val prices = uberCall.execute().body()
            val domainDestination: SearchRidesResponse? = SearchRidesResponse(RideMapper().transform(costEstimates?.rides, prices?.rides))

            if(domainDestination != null) callback.onSuccess(domainDestination) else callback.onFailure(WebServiceException(null))
        })
    }
}