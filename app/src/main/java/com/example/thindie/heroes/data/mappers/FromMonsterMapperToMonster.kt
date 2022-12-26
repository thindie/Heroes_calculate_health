package com.example.thindie.heroes.data.mappers

import com.example.thindie.heroes.data.local.RawResourceReader
import com.example.thindie.heroes.domain.entities.Monster

class FromMonsterMapperToMonster(private val creature: RawResourceReader.MonsterMapper) {
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
            IMG_url = creature.IMG_url
        )
    }
}