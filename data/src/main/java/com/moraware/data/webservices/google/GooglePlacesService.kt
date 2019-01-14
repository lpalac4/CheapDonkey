package com.moraware.data.webservices.google

import com.moraware.data.models.Place
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=YOUR_API_KEY
 *
 * https://maps.googleapis.com/maps/api/place/textsearch/xml?query=restaurants+in+Sydney&key=YOUR_API_KEY
 */
interface GooglePlacesService {

    @GET("json?inputtype=textquery&fields=formatted_address,geometry")
    fun searchLocation(@Query("key") key: String, @Query("query") textQuery: String): Call<List<Place>>
}