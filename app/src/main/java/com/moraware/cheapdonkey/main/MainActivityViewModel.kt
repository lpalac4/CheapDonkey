package com.moraware.cheapdonkey.main

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.location.Location
import com.moraware.cheapdonkey.BR
import com.moraware.cheapdonkey.base.BaseViewModel
import com.moraware.cheapdonkey.util.SingleLiveEvent
import com.moraware.domain.models.Ride
import com.moraware.domain.models.TaxiService
import com.moraware.domain.usecase.retrievealltaxiservices.RetrieveTaxiServicesFailure
import com.moraware.domain.usecase.retrievealltaxiservices.RetrieveTaxiServicesResponse
import com.moraware.domain.usecase.retrievealltaxiservices.RetrieveTaxiServicesUseCase
import com.moraware.domain.usecase.searchlocation.SearchLocationFailure
import com.moraware.domain.usecase.searchlocation.SearchLocationResponse
import com.moraware.domain.usecase.searchlocation.SearchLocationUseCase
import com.moraware.domain.usecase.searchrides.SearchRidesFailure
import com.moraware.domain.usecase.searchrides.SearchRidesResponse
import com.moraware.domain.usecase.searchrides.SearchRidesUseCase
import java.util.logging.Level

class MainActivityViewModel : BaseViewModel() {

    var taxiServices: MutableLiveData<List<TaxiService>> = MutableLiveData()
    var possibleDestinations: MutableLiveData<List<String>> = MutableLiveData()
    var rides: MutableLiveData<List<Ride>> = MutableLiveData()

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

    private var mSearchingDestination: Boolean = false

    @Bindable
    fun isSearchingDestination(): Boolean {
        return mSearchingDestination
    }
    fun setSearchingDestination(value: Boolean) {
        if(mSearchingDestination != value){
            mSearchingDestination = value
            notifyPropertyChanged(BR.searchingDestination)
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
    private fun onRetrieveTaxiServicesSuccess(response: RetrieveTaxiServicesResponse) {
        setProcessing(false)

        taxiServices.value = response.taxiServices
        setTaxiServicesReadyForQuery(true)
    }

    private fun onRetrieveTaxiServicesFailure(failure: RetrieveTaxiServicesFailure) {
        setProcessing(false)
        setHasError(true)
        setErrorMessage("Your requested destination was not found.")
    }

    /**
     * Once the user enters a destination address into the search box we will query for the most accurate
     * possible addresses.
     */
    fun searchDestination(address: String?) {
        mLogger.log(Level.FINE, "Searching for requested address.")
        setSearchingDestination(true)

        var useCase = SearchLocationUseCase()
        useCase.mRequest.address = address
        mUseCaseClient.execute({ it.either(::onSearchAddressDestinationFailure, ::onSearchAddressDestinationSuccess)}, useCase)
    }

    private fun onSearchAddressDestinationSuccess(response: SearchLocationResponse) {
        setSearchingDestination(false)
        possibleDestinations.value = response.possibleAddresses
    }

    private fun onSearchAddressDestinationFailure(failure: SearchLocationFailure) {
        setSearchingDestination(false)
        setHasError(true)
        setErrorMessage("Your requested destination was not found.")
    }

    /**
     * Initiate a scan for the current location using device services. Once devices services
     * have give us a location, store it for later use
     */

    // this single live event will trigger the hardware check
    val _getCurrentLocation: SingleLiveEvent<Unit> = SingleLiveEvent()

    // this method will be called by the ui button
    fun getCurrentLocation() {
        _getCurrentLocation.call()
    }

    fun setCurrentLocation(mLocation: Location?) {
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

    fun onFinalDestinationSelected(destination: String) {
        mLogger.log(Level.FINE, "Searching for ride estimates.")
        setSearchingRides(true)

        var useCase = SearchRidesUseCase()
        useCase.mRequest.startAddress = destination
        useCase.mRequest.endLocationLatitude = mLocation?.latitude
        useCase.mRequest.endLocationLongitude = mLocation?.longitude
        mUseCaseClient.execute({ it.either(::onSearchRideEstimatesFailure, ::onSearchRideEstimatesSuccess)}, useCase)
    }

    private fun onSearchRideEstimatesSuccess(response: SearchRidesResponse) {
        setSearchingRides(false)
        rides.value = response.rides
    }

    private fun onSearchRideEstimatesFailure(failure: SearchRidesFailure) {
        setSearchingRides(false)
        setHasError(true)
        setErrorMessage("An error occurred retrieving rides.")
    }
}