package com.example.thindie.heroes.domain.entities

data class Dwelling(
    val fraction: Fraction,
    val growth: Int,
    val level: MonsterLevel,
    val populationGiver: PopulationGiver
)
