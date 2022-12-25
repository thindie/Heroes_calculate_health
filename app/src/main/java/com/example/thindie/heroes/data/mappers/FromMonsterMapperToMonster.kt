package com.example.thindie.heroes.data.mappers

import com.example.thindie.heroes.data.local.FractionData
import com.example.thindie.heroes.data.local.LocalFileReader
import com.example.thindie.heroes.domain.entities.Monster

class FromMonsterMapperToMonster(private val creature: LocalFileReader.MonsterMapper) {
    init {
        invoke()
    }

    private fun invoke() {
        creature.fraction = FractionData().getFraction(creature.name!!)

    }


    fun map(): Monster {

        return Monster(
            name = creature.name!!,
            attack = creature.attack!!,
            defence = creature.defence!!,
            damageFrom = creature.damageFrom!!,
            damageTo = creature.damageTo!!,
            health = creature.health!!,
            growth = creature.growth!!,
            cost = creature.cost!!,
            speed = creature.speed!!,
            level = creature.level!!,
            fraction = creature.fraction!!,
            IMG_url = creature.IMG_url

        )
    }
}