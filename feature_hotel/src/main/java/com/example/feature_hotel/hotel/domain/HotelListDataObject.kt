package com.example.feature_hotel.hotel.domain

data class HotelListDataObject(
    val hotelName: String,
    val hotelCity: String,
    val hotelImage: Int,
    val isFavorite: Boolean,
    val hotelRating: String,
    val hotelDistance: String,
    val hotelPrice: String
)
