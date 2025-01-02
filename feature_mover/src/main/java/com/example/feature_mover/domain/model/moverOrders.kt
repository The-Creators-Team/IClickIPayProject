package com.example.feature_mover.domain.model


data class moverOrders(
    val startAddress: String,
    val startRooms: String,
    val startFloors: String,
    val startLift: Boolean,
    val endAddress: String,
    val endFloors: String,
    val endLift: Boolean,
    val selectedDate: java.time.LocalDate,
    val selectedTime: String?
    )
