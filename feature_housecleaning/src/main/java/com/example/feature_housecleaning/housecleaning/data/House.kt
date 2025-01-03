package com.example.feature_housecleaning.housecleaning.data

data class House(
    val name: String,
    var areaPerMeterSquared: Int,
    var roomCount: Int,
    var bathroomCount: Int,
    var ironingCount: String,
    var availability: Int,
    var date:String
)
