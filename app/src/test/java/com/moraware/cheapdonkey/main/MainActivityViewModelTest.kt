package com.moraware.cheapdonkey.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.moraware.cheapdonkey.base.BaseViewModel
import com.moraware.cheapdonkey.dependencyinjection.DaggerTestComponent
import com.moraware.cheapdonkey.dependencyinjection.TestComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


open class MainActivityViewModelTest {

    var viewModel: MainActivityViewModel? = null

    @Rule @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var component: TestComponent

    @Before
    fun setUp() {
        component = DaggerTestComponent.builder().build()
        component.inject(this)
    }

    @Test
    fun searchRides() {
        viewModel = givenCurrentLocationAndDestinationIsKnown()
        Assert.assertTrue(viewModel?.searchRides()!!)

        viewModel = givenCurrentLocationIsNotKnownAndDestinationIsKnown()
        Assert.assertFalse(viewModel?.searchRides()!!)

        viewModel = givenCurrentLocationIsNotKnownAndDestinationIsNotKnown()
        Assert.assertFalse(viewModel?.searchRides()!!)

        viewModel = givenCurrentLocationKnownAndDestinationIsNotKnown()
        Assert.assertFalse(viewModel?.searchRides()!!)
    }

    private fun givenCurrentLocationAndDestinationIsKnown() : MainActivityViewModel? {
        viewModel = MainActivityViewModel()
        component.inject(viewModel as BaseViewModel)
        viewModel?.onFinalDestinationSelected("Test Address", LatLng(40.00, 80.00))
        viewModel?.setCurrentLocation(Location("GPS"))
        return viewModel
    }

    private fun givenCurrentLocationKnownAndDestinationIsNotKnown() : MainActivityViewModel? {
        viewModel = MainActivityViewModel()
        component.inject(viewModel as BaseViewModel)
        viewModel?.setCurrentLocation(Location("GPS"))
        return viewModel
    }

    private fun givenCurrentLocationIsNotKnownAndDestinationIsKnown() : MainActivityViewModel? {
        viewModel = MainActivityViewModel()
        component.inject(viewModel as BaseViewModel)
        viewModel?.onFinalDestinationSelected("Test Address", LatLng(40.00, 80.00))
        return viewModel
    }

    private fun givenCurrentLocationIsNotKnownAndDestinationIsNotKnown() : MainActivityViewModel? {
        viewModel = MainActivityViewModel()
        component.inject(viewModel as BaseViewModel)
        return viewModel
    }
}