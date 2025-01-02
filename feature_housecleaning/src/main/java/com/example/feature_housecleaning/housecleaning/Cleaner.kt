package com.example.feature_housecleaning.housecleaning

data class Cleaner(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Int,
    val costPerHour: Int
)
