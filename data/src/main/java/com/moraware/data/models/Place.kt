package com.moraware.data.models

import java.util.*

data class Place(val formatted_address: String,
                 val geometry: Array<Location>) {

    data class Location(val lat: Double,
                        val lng: Double)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Place

        if (formatted_address != other.formatted_address) return false
        if (!Arrays.equals(geometry, other.geometry)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = formatted_address.hashCode()
        result = 31 * result + Arrays.hashCode(geometry)
        return result
    }
}