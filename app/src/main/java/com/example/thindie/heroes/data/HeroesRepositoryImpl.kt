package com.example.thindie.heroes.data

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.*

class HeroesRepositoryImpl : HeroesRepository {
    override fun getFraction(fraction: Fraction): List<Monster> {
        TODO("Not yet implemented")
    }

    override fun acceptMonster(): Monster {
        TODO("Not yet implemented")
    }

    override fun calculateGrowth(
        week: Week,
        populationGiver: PopulationGiver,
        list: List<Monster>
    ): HealthPoints {
        TODO("Not yet implemented")
    }

    override fun accumulateHealthPoints(healthPoints: HealthPoints): HealthPoints {
        TODO("Not yet implemented")
    }

}