package com.example.feature_pcrepair.pcrepair

data class PcRepairTechnician(
    val name: String,
    val location: String,
    val rating: Double,
    val distance: Int,
    val price: Int,
    val description: String,
    val image: Int = 0
)