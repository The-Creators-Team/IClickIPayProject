package com.example.feature_housecleaning.housecleaning.data

data class Cleaner(
    val name: String,
    val desc:String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Double,
    val roomCost: Double,
    val bathroomCost: Double,
    val ironing: Double,
    val costPerHour: Double
)
