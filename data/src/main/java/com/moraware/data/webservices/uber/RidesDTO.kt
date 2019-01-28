package com.moraware.data.webservices.uber

import com.google.gson.annotations.SerializedName

class RidesDTO(@SerializedName("prices") val rides: Array<Price>) {
}