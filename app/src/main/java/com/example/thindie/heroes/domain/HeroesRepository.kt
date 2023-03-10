package com.example.thindie.heroes.domain

import com.example.thindie.heroes.domain.entities.*

interface HeroesRepository {

    fun getAllCreatures() : List<Monster>

    fun getAllFractions() : List<FractionToImage>

    fun getFraction(fraction: Fraction): List<Monster>

    fun acceptMonster(name : String): Monster

    fun calculateGrowthHealth(
        week: Week,
        list: List<Monster>
    ): HealthPoints

    fun collectAllCountableMonsters(allMonsterList: List<Monster>) : List<Monster>

    fun accumulateHealthPoints(vararg healthPoints: HealthPoints): HealthPoints

    fun accumulateGold(list: List<Monster>, week: Week) : Int
}