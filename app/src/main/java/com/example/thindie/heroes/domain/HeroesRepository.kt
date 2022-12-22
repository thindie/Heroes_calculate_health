package com.example.thindie.heroes.domain

import com.example.thindie.heroes.domain.entities.*

interface HeroesRepository {

    fun getFraction(fraction: Fraction): List<Monster>

    fun acceptMonster(name : String): Monster

    fun calculateGrowthHealth(
        week: Week,
        list: List<Monster>
    ): HealthPoints

    fun accumulateHealthPoints(vararg healthPoints: HealthPoints): HealthPoints
}