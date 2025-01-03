package com.example.feature_mover.domain.model

data class Mover(
    val id: Int,
    val name: String,
    val rating: Double,
    val location: String,
    val price: Double
)