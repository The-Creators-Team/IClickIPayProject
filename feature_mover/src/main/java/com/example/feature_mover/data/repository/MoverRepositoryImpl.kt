package com.example.feature_mover.data.repository

import com.example.feature_mover.data.datasoruce.JsonDataSoruce
import com.example.feature_mover.data.mapper.toDomain
import com.example.feature_mover.domain.model.Mover
import com.example.feature_mover.domain.repository.MoverRepository

class MoverRepositoryImpl(private val datasource: JsonDataSoruce) : MoverRepository {
    private var movers : MutableList<Mover> = datasource.getMovers().map { it.toDomain() }.toMutableList()

    override suspend fun getMovers(): List<Mover> =movers

}