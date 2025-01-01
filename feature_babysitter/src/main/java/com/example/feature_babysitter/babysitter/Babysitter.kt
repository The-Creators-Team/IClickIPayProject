package com.example.feature_babysitter.babysitter

data class Babysitter(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Int,
    val costPerHour: Int
)
