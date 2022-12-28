package com.example.thindie.heroes.data.mappers

import com.example.thindie.heroes.domain.entities.*

class GrowthMapper {
    fun fromDwellingToHealth(
        dwelling: Dwelling,
        week: Week,
    ): HealthPoints {
        return HealthPoints(
            dwelling.growth
                    * week.weekNumber
                    * PopulationGiver.multiplier(dwelling::class.java)
        )
    }

    fun fromMonsterToHealth(
        monster: Monster,
        week: Week,
    ): HealthPoints {
        return HealthPoints(
            monster.growth
                    * monster.health
                    * week.weekNumber
                    * PopulationGiver.multiplier(monster::class.java)
        )
    }

    fun fromPopulationArtifactToHealth(populationArtifacts: PopulationArtifacts): HealthPoints {
        TODO()
    }
}