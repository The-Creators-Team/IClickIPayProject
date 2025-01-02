package com.example.feature_babysitter.babysitter.data

data class Babysitter(
    val name: String,
    val desc:String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val latitude: Double,
    val longitude: Double,
    val distance : Double,
    val costPerHour: Double,
)
