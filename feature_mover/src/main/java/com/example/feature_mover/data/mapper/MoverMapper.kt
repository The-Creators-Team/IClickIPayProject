package com.example.feature_mover.data.mapper

import com.example.feature_mover.data.model.MoverDTO
import com.example.feature_mover.domain.model.Mover

fun MoverDTO.toDomain() = Mover(
    id = id,
    name = name,
    rating = rating,
    location = location,
    price = price
)

fun Mover.toDTO() = MoverDTO(
    id = id,
    name = name,
    rating = rating,
    location = location,
    price=price
)

