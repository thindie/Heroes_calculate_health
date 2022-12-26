package com.example.thindie.heroes.data

import android.app.Application
import com.example.thindie.heroes.data.local.FractionData
import com.example.thindie.heroes.data.local.RawResourceReader
import com.example.thindie.heroes.data.mappers.GrowthMapper
import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.HealthPoints
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.Week

class HeroesRepositoryImpl(application: Application) : HeroesRepository {
    private val growthMapper = GrowthMapper()
    private val fractionData = FractionData(RawResourceReader(application))

    override fun getAllCreatures(): List<Monster> {
        return fractionData.getAllCreatures()
    }

    override fun getFraction(fraction: Fraction): List<Monster> {
        return fractionData.representFraction(fraction)
    }

    override fun acceptMonster(name: String): Monster {
       return fractionData.getSingleMonsterByName(name)
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