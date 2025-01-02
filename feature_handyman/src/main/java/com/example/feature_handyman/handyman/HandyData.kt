package com.example.feature_handyman.handyman

data class HandyData(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Int,
    val costPerHour: Int,
)