package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.HealthPoints

class AccumulateHealthPointsUseCase(private val heroesRepository: HeroesRepository) {
    fun accumulateHealthPoints(healthPoints: HealthPoints): HealthPoints {
        return heroesRepository.accumulateHealthPoints(healthPoints)
    }

}