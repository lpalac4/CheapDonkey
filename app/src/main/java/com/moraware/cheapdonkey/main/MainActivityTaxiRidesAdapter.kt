package com.moraware.cheapdonkey.main

import com.morasoftware.mango.bindingadapters.BaseRecyclerviewDataBindingAdapter
import com.moraware.cheapdonkey.R
import com.moraware.cheapdonkey.base.BaseViewModel
import com.moraware.domain.models.Ride

class MainActivityTaxiRidesAdapter(private var ridesAvailable: ArrayList<Ride>, viewModel: BaseViewModel) : BaseRecyclerviewDataBindingAdapter(viewModel) {
    override fun getObjForPosition(position: Int): Any {
        return ridesAvailable[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.main_adapter_rides
    }

    override fun getItemCount(): Int {
        return ridesAvailable.size
    }

    fun setRides(newRides: List<Ride>) {
        ridesAvailable.clear()
        ridesAvailable.addAll(newRides)
        notifyDataSetChanged()
    }
}