package com.moraware.data.models

import com.google.gson.annotations.SerializedName

data class TaxiService(
        @SerializedName("name") val name: String = "",
        @SerializedName("apiUrl") val apiUrl: String = "",
        @SerializedName("token") val token: String = "",
        @SerializedName("logoUrl") val logoUrl: String = "")