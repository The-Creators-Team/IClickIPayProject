package com.examole.feature_mechanic.domain.repository

import com.examole.feature_mechanic.domain.model.Mechanic


interface MechanicsRepository {
    suspend fun getMovers(): List<Mechanic>

}