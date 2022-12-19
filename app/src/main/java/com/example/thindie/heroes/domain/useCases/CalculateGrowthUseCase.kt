package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.HealthPoints
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.PopulationGiver
import com.example.thindie.heroes.domain.entities.Week

class CalculateGrowthUseCase(private val heroesRepository: HeroesRepository) {
    fun calculateGrowth(
        week: Week,
        populationGiver: PopulationGiver,
        list: List<Monster>
    ): HealthPoints {
        return heroesRepository.calculateGrowth(
            week,
            populationGiver,
            list
        )
    }
}