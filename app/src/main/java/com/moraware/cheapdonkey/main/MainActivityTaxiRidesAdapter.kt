package com.moraware.cheapdonkey.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.moraware.domain.models.Ride

class MainActivityTaxiRidesAdapter(private var ridesAvailable: List<Ride>) : RecyclerView.Adapter<MainActivityTaxiRidesAdapter.TaxiServiceViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): TaxiServiceViewHolder {

        return TaxiServiceViewHolder(viewGroup, ridesAvailable[pos])
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(p0: TaxiServiceViewHolder, p1: Int) {

    }

    data class TaxiServiceViewHolder(val viewGroup: ViewGroup,
                                     val ride: Ride) : RecyclerView.ViewHolder(viewGroup)
}