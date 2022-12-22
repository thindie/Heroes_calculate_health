package com.example.thindie.heroes.domain.entities

data class Dwelling(
    val dwellingName: String,
    val monster: Monster,
    val fraction: Fraction = monster.fraction,
    val growth: Int = monster.growth,
    val IMG_url : String?
)
