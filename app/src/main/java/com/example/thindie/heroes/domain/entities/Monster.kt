package com.example.thindie.heroes.domain.entities

import com.example.thindie.heroes.domain.CHECKED
import com.example.thindie.heroes.domain.EXPANDED

data class Monster (
    val name: String,
    val attack: Int,
    val defence: Int,
    val damageFrom: Int,
    val damageTo: Int,
    val health : Int,
    var growth: Int,
    val cost: Int,
    val speed: Int,
    var fraction: Fraction = Fraction.CITADEL,
    val level: MonsterLevel,
    var IMG_url: String?,
    val checkedToCalculate: Pair<Boolean, String> = false to CHECKED,
    val expandToDetailView: Pair<Boolean, String> = false to EXPANDED
)

