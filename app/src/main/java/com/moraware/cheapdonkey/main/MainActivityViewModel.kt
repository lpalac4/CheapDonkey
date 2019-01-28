package com.moraware.cheapdonkey.main

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.moraware.cheapdonkey.BR
import com.moraware.cheapdonkey.base.BaseViewModel
import com.moraware.cheapdonkey.util.SingleLiveEvent
import com.moraware.domain.models.Ride
import com.moraware.domain.models.Taxi
import com.moraware.domain.usecase.retrievealltaxiservices.GetTaxiServices
import com.moraware.domain.usecase.retrievealltaxiservices.GetTaxiServicesFailure
import com.moraware.domain.usecase.retrievealltaxiservices.RetrieveTaxiServicesUseCase
import com.moraware.domain.usecase.searchrides.SearchRides
import com.moraware.domain.usecase.searchrides.SearchRidesFailure
import com.moraware.domain.usecase.searchrides.SearchRidesUseCase
import java.util.logging.Level

class MainActivityViewModel : BaseViewModel() {

    var taxiServices: MutableLiveData<List<Taxi>> = MutableLiveData()
    var rides: MutableLiveData<List<Ride>> = MutableLiveData()
    var destination: String = ""
    private var destinationLatLng: LatLng? = null
    var mLocation: Location? = null

    private var mTaxiServicesReadyForQuery: Boolean = false

    @Bindable
    fun isTaxiServicesReadyForQuery(): Boolean {
        return mTaxiServicesReadyForQuery
    }

    fun setTaxiServicesReadyForQuery(value: Boolean) {
        if(mTaxiServicesReadyForQuery != value){
            mTaxiServicesReadyForQuery = value
            notifyPropertyChanged(BR.taxiServicesReadyForQuery)
        }
    }

    private var mCurrentLocationKnown: Boolean = false

    @Bindable
    fun isCurrentLocationKnown(): Boolean {
        return mCurrentLocationKnown
    }
    fun setCurrentLocationKnown(value: Boolean) {
        if(mCurrentLocationKnown != value){
            mCurrentLocationKnown = value
            notifyPropertyChanged(BR.currentLocationKnown)
        }
    }

    override fun loadData() {
        mLogger.log(Level.FINE, "Loading main activity view model data....")

        setProcessing(true)

        var useCase = RetrieveTaxiServicesUseCase()
        mUseCaseClient.execute({ it.either(::onRetrieveTaxiServicesFailure, ::onRetrieveTaxiServicesSuccess)}, useCase)
    }

    /**
     * On activity start we will make sure ride sharing services are available
     */
    private fun onRetrieveTaxiServicesSuccess(response: GetTaxiServices) {
        setProcessing(false)

        taxiServices.value = response.taxiServices
        setTaxiServicesReadyForQuery(true)
        updateCurrentLocationEvent.call()
    }

    private fun onRetrieveTaxiServicesFailure(failure: GetTaxiServicesFailure) {
        setProcessing(false)
        setHasError(true)
        setErrorMessage("Your requested destination was not found.")
    }

    /**
     * Initiate a scan for the current location using device services. Once devices services
     * have give us a location, store it for later use
     */

    // this single live event will trigger the hardware check
    val updateCurrentLocationEvent: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun setCurrentLocation(location: Location?) {
        mLocation = location

        if(mLocation == null) {
            mLogger.log(Level.FINE, "Current location unknown.")
            return
        }

        setCurrentLocationKnown(true)
    }

    /**
     * Once the user selects a final destination from possible addresses, the ride query fun
     * begins.
     */

    private var searchingRides: Boolean = false

    @Bindable
    fun isSearchingRides(): Boolean {
        return searchingRides
    }
    fun setSearchingRides(value: Boolean) {
        if(searchingRides != value){
            searchingRides = value
            notifyPropertyChanged(BR.searchingRides)
        }
    }

    fun onFinalDestinationSelected(destination: String, latLng: LatLng) {
        mLogger.log(Level.FINE, "Searching for ride estimates.")

        this.destination = destination
        this.destinationLatLng = latLng

        updateCurrentLocationEvent.call()
    }

    fun searchRides() : Boolean {
        if(destination == "" || mLocation == null) {
            setErrorMessage("Cannot search rides without knowing destination and current location: \n destination: $destination \n location: $mLocation")
            mLogger.log(Level.FINE, "Cannot search rides without knowing destination and current location: \n destination: $destination \n location: $mLocation")
            return false
        }

        setSearchingRides(true)
        var useCase = SearchRidesUseCase(destination, destinationLatLng?.latitude, destinationLatLng?.longitude, mLocation?.latitude, mLocation?.longitude)
        mUseCaseClient.execute({ it.either(::onSearchRideEstimatesFailure, ::onSearchRideEstimatesSuccess)}, useCase)
        return true
    }

    private fun onSearchRideEstimatesSuccess(response: SearchRides) {
        setSearchingRides(false)
        rides.value = response.rides
    }

    private fun onSearchRideEstimatesFailure(failure: SearchRidesFailure) {
        setSearchingRides(false)
        setHasError(true)
        setErrorMessage("An error occurred retrieving rides.")
    }

    /**
     * Start one of the apps by clicking on the thumbnails
     */
    val startUberAppCall: SingleLiveEvent<Unit> = SingleLiveEvent()
    val startLyftAppCall: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun startUberApp() {
        startUberAppCall.call()
    }

    fun startLyftApp() {
        startLyftAppCall.call()
    }
}