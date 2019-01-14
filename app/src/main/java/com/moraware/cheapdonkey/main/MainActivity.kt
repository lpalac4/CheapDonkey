package com.moraware.cheapdonkey.main

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import com.moraware.cheapdonkey.R
import com.moraware.cheapdonkey.base.ViewModelActivity
import com.moraware.cheapdonkey.databinding.ActivityMainBinding
import java.util.logging.Level


class MainActivity : ViewModelActivity<MainActivityViewModel>() {
    private lateinit var binding: ActivityMainBinding

    private val PERMISSIONS_LOCATION_CODE: Int = 1114
    private var mLocation: Location? = null

    override fun setupUIAndBindViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mViewModel
        mViewModel.taxiServices.observe(this, Observer { it ->

            it?.let {
                for(taxiServices in it) {
                    // fill image views with proper service info
                }
            }
        })

        mViewModel._getCurrentLocation.observe(this, Observer {
            getLastLocation()
            mViewModel.setCurrentLocation(mLocation)
        })

        verifyLocationPermission()
        setupPlaceAutocomplete()
    }

    private fun setupPlaceAutocomplete() {
        if (supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment) == null) return

        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as SupportPlaceAutocompleteFragment
        autocompleteFragment.setHint(resources.getString(R.string.main_destination_hint))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                mLogger.log(Level.FINE, "Place: " + place.name)
                mViewModel.onFinalDestinationSelected(place.name.toString(), place.latLng)
            }

            override fun onError(status: Status) {
                mLogger.log(Level.FINE, "An error occurred: $status")
            }
        })
    }

    private fun verifyLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                // show dialog explaining why message required
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        PERMISSIONS_LOCATION_CODE)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    }
}
