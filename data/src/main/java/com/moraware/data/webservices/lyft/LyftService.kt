package com.moraware.data.webservices.lyft

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LyftService {

    @Headers(
        "Authorization: bearer OdON2zaiPaIbg2ofbxN76gnEuX8JFlwzEnDNInfL4qPrBD4twMnVJ4beC4nQ7Ym3kaz+jLUQ52XI+svckUNZUkCJ5sWzTJEBuxRCvuv0ivRvLRgowbPMofw="
    )

    @GET("v1/cost/")
    fun getRideEstimate(@Query("start_lat") startLat: Float,
                        @Query("start_lng") startLng: Float,
                        @Query("end_lat") endLat: Float,
                        @Query("end_lng") endLng: Float): Call<RidesDTO>
}