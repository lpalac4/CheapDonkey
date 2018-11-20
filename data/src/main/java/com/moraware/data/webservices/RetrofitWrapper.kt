package com.moraware.data.webservices

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitWrapper {

    private val parameters: String = ""
    val searchLocationRequestUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/?$parameters"

//    @GET("place/findplacefromtext/$output?$parameters")
//    fun searchArtist(@Query("artist") artist: String): Call
}