package com.example.common.reuseable.maps.mapmodels

import com.google.gson.annotations.SerializedName

data class GeocodedWaypointModel(
    @SerializedName("geocoder_status")
    val geocoderStatus: String? = ""
)