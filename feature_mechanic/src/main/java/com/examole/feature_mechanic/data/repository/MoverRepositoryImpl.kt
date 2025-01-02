package com.examole.feature_mechanic.data.repository

import com.examole.feature_mechanic.data.datasoruce.JsonDataSoruce
import com.examole.feature_mechanic.data.mapper.toDomain
import com.examole.feature_mechanic.domain.model.Mechanic
import com.examole.feature_mechanic.domain.repository.MechanicsRepository


class MechanicsRepositoryImpl(private val datasource: JsonDataSoruce) : MechanicsRepository {
    private var movers : MutableList<Mechanic> = datasource.getMovers().map { it.toDomain() }.toMutableList()

    override suspend fun getMovers(): List<Mechanic> =movers

}