package com.example.thindie.heroes.data.mappers

import com.example.thindie.heroes.domain.entities.*

class GrowthMapper {
    fun fromDwellingToHealth(
        dwelling: Dwelling,
        week: Week,
    ): HealthPoints {
        return HealthPoints(
                    (dwelling.monster.growth * PopulationGiver.multiplier(dwelling, null))
                    * week.weekNumber
                            *dwelling.growth
        )
    }

    fun fromMonsterToHealth(
        monster: Monster,
        week: Week,
    ): HealthPoints {
        return HealthPoints(

                    ( monster.growth * PopulationGiver.multiplier(null, monster))
                    * monster.health
                    * week.weekNumber

        )
    }

    fun fromPopulationArtifactToHealth(populationArtifacts: PopulationArtifacts): HealthPoints {
        TODO()
    }
}