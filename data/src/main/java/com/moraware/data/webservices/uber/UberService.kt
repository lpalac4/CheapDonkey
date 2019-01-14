package com.moraware.data.webservices.uber

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UberService {

    @GET("/v1.2/estimates/price")
    fun getRideEstimate(@Query("start_latitude") startLat: Float,
            @Query("start_longitude") startLng: Float,
            @Query("end_latitude") endLat: Float,
            @Query("end_longitude") endLng: Float): Call<List<Prices>>
}