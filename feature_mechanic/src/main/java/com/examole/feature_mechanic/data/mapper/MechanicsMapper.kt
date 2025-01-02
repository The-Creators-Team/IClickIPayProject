package com.examole.feature_mechanic.data.mapper

import com.examole.feature_mechanic.data.model.MechanicDTO
import com.examole.feature_mechanic.domain.model.Mechanic


fun MechanicDTO.toDomain() = Mechanic(
    id = id,
    name = name,
    rating = rating,
    location = location
)

fun Mechanic.toDTO() = MechanicDTO(
    id = id,
    name = name,
    rating = rating,
    location = location
)

