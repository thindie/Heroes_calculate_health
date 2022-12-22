package com.example.thindie.heroes.data

import com.example.thindie.heroes.data.mappers.GrowthMapper
import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.HealthPoints
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.Week

class HeroesRepositoryImpl : HeroesRepository {
    private val growthMapper = GrowthMapper()
    override fun getFraction(fraction: Fraction): List<Monster> {
        return TODO()
    }

    override fun acceptMonster(name : String): Monster {
        TODO("Not yet implemented")
    }

    override fun calculateGrowthHealth(
        week: Week,
        list: List<Monster>
    ): HealthPoints {
        var points = 0
        list.forEach { points += growthMapper.fromMonsterToHealth(it, week).health }
        return HealthPoints(points)
    }

    override fun accumulateHealthPoints(vararg healthPoints: HealthPoints): HealthPoints {
        var points = 0
        healthPoints.forEach { points += it.health }
        return HealthPoints(points)
    }

}