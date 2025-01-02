package com.example.feature_mover.domain.repository

import com.example.feature_mover.domain.model.Mover

interface MoverRepository {
    suspend fun getMovers(): List<Mover>

}