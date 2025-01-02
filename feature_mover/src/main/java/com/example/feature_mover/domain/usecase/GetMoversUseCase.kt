package com.example.feature_mover.domain.usecase

import com.example.feature_mover.domain.model.Mover
import com.example.feature_mover.domain.repository.MoverRepository

class GetMoversUseCase(private val moverRepository: MoverRepository) {
    suspend operator fun invoke(): List<Mover> = moverRepository.getMovers()
}