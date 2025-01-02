package com.example.feature_delivery.delivery

data class DeliveryData(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Int,
    val costPerHour: Int,
)