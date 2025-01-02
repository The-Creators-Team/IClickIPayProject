package com.examole.feature_mechanic.domain.usecase

import com.examole.feature_mechanic.domain.model.Mechanic
import com.examole.feature_mechanic.domain.repository.MechanicsRepository


class GetMechanicsUseCase(private val moverRepository: MechanicsRepository) {
    suspend operator fun invoke(): List<Mechanic> = moverRepository.getMovers()
}