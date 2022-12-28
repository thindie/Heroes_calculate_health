package com.example.thindie.heroes.data

import android.app.Application
import com.example.thindie.heroes.data.local.FractionData
import com.example.thindie.heroes.data.local.RawResourceReader
import com.example.thindie.heroes.data.mappers.GrowthMapper
import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.*

class HeroesRepositoryImpl(application: Application) : HeroesRepository {
    private val growthMapper = GrowthMapper()
    private val fractionData = FractionData(RawResourceReader(application))



    override fun getAllCreatures(): List<Monster> {
        return fractionData.getAllCreatures()
    }

    override fun getAllFractions(): List<FractionPair>  {
        return fractionData.getFractionImage()
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

    override fun collectAllCountableMonsters(): List<Monster> {
        val resultList = mutableListOf<Monster>()
        val allMonsterList = getAllCreatures()
        allMonsterList.forEach {
            monster ->
            if(monster.checkedToCalculate.first){
                resultList.add(monster)
            }
        }
        return resultList.toList()
    }


    override fun accumulateHealthPoints(vararg healthPoints: HealthPoints): HealthPoints {
        var points = 0
        healthPoints.forEach { points += it.health }
        return HealthPoints(points)
    }

}