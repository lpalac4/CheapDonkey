package com.moraware.data.webservices.lyft

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LyftService {

    @GET("/v1/cost/")
    fun getRideEstimate(@Query("start_lat") startLat: Float,
                        @Query("start_lng") startLng: Float,
                        @Query("end_lat") endLat: Float,
                        @Query("end_lng") endLng: Float): Call<List<CostEstimates>>
}