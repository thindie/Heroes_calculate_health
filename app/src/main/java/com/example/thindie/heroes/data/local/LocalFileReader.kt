package com.example.thindie.heroes.data.local

import com.example.thindie.heroes.data.mappers.FromMonsterMapperToMonster
import com.example.thindie.heroes.data.mappers.FromNumToLevel
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.MonsterLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class LocalFileReader {
    suspend fun readFromCreaturesFile(): List<Monster> {
        val monsterList: MutableList<Monster> = mutableListOf()
        withContext(Dispatchers.IO) {

            val reader =
                BufferedReader(InputStreamReader(FileInputStream("com/example/thindie/heroes/data/local/heroes3.txt")))
            while (reader.ready()) {
                val line = reader.readLine()
                val listFormLine = line.split(" ")
                val rawMonster = MonsterMapper()
                for (i in CREATURES_CARETTE_COUNTER..MONSTER_FIELDS_COUNT) {
                    when (i) {
                        0 -> rawMonster.name = listFormLine[i]
                        1 -> rawMonster.IMG_url = listFormLine[i]
                        2 -> rawMonster.name = listFormLine[i]
                        3 -> rawMonster.level = FromNumToLevel().invoke(listFormLine[i])
                        4 -> rawMonster.attack = listFormLine[i].toInt()
                        5 -> rawMonster.defence = listFormLine[i].toInt()
                        6 -> rawMonster.damageFrom = listFormLine[i].toInt()
                        7 -> rawMonster.damageTo = listFormLine[i].toInt()
                        8 -> rawMonster.health = listFormLine[i].toInt()
                        9 -> rawMonster.growth = listFormLine[i].toInt()
                        10 -> rawMonster.cost = listFormLine[i].toInt()
                        11 -> rawMonster.speed = listFormLine[i].toInt()
                    }
                }
                monsterList.add(FromMonsterMapperToMonster(rawMonster).map())
            }
        }
        return monsterList
    }

    data class MonsterMapper(
        var name: String? = "",
        var attack: Int? = 0,
        var defence: Int? = 0,
        var damageFrom: Int? = 0,
        var damageTo: Int? = 0,
        var health: Int? = 0,
        var growth: Int? = 0,
        var cost: Int? = 0,
        var speed: Int? = 0,
        var fraction: Fraction? = Fraction.CITADEL,
        var level: MonsterLevel? = MonsterLevel.SIX,
        var IMG_url: String? = ""
    )


    companion object {
        private const val CREATURES_CARETTE_COUNTER = 0
        private const val MONSTER_FIELDS_COUNT = 12
    }
}