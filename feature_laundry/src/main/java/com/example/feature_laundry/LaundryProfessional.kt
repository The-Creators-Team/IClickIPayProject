package com.example.feature_laundry

data class LaundryProfessional(
    val name: String,
    val location: String,
    val rating: Double,
    val distance: Int,
    val price: Int,
    val description: String,
    val image: Int = 0
)