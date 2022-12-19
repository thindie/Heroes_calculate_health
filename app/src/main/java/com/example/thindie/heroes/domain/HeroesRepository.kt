package com.example.thindie.heroes.domain

import com.example.thindie.heroes.domain.entities.*

interface HeroesRepository {

    fun getFraction(fraction: Fraction): List<Monster>

    fun acceptMonster(): Monster

    fun calculateGrowth(
        week: Week,
        populationGiver: PopulationGiver,
        list: List<Monster>
    ): HealthPoints

    fun accumulateHealthPoints(healthPoints : HealthPoints): HealthPoints

}