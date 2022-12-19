package com.example.thindie.heroes.data

import com.example.thindie.heroes.domain.entities.*

class GrowthMapper {
    fun fromDwellingToHealth(
        dwelling: Dwelling,
        week: Week,
        populationGiver: PopulationGiver
    ): HealthPoints {
        TODO()
    }

    fun fromMonsterToHealth(
        monster: Monster,
        week: Week,
        populationGiver: PopulationGiver
    ): HealthPoints {
        TODO()
    }

    fun fromPopulationArtifactToHealth(populationArtifacts: PopulationArtifacts) : HealthPoints {
        TODO()
    }
}