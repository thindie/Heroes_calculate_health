package com.example.thindie.heroes.domain.entities

data class Dwelling(
    val dwellingName: String,
    val fraction: Fraction,
    val growth: Int,
    val monster: Monster,
    val IMG_url : String
)
