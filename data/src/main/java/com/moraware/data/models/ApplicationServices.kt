package com.moraware.data.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.io.InputStream

class ApplicationServices {

    @SerializedName("taxiServices")
    var taxiServices: List<TaxiService> = listOf()

    @SerializedName("locationAPIKey")
    var locationAPIKey: String = ""

    companion object {
        private var sInstance: ApplicationServices? = null

        fun initialize(inputStream: InputStream) {
            var json = inputStreamToString(inputStream)
            sInstance = Gson().fromJson(json, ApplicationServices::class.java)
        }

        fun getInstance(): ApplicationServices? {
            return sInstance
        }

        private fun inputStreamToString(inputStream: InputStream): String {
            return try {
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes, 0, bytes.size)
                String(bytes)
            } catch (e: IOException) {
                ""
            }
        }
    }
}