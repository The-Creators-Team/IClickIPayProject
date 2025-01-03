package com.example.feature_handyman.handyman.data

data class HandyData(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Float, // Rating from 1 to 5
    val distance: Float,
    val price: Float,    // Price, e.g., hourly rate
    val popularity: Int // Popularity based on some metric (like number of reviews or job completions)
)