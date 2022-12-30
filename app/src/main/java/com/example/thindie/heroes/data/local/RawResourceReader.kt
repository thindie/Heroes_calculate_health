package com.example.thindie.heroes.data.local

import android.app.Application
import com.example.thindie.heroes.R
import com.example.thindie.heroes.data.mappers.FromMonsterMapperToMonster
import com.example.thindie.heroes.data.mappers.FromNumToLevel
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.MonsterLevel
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader

class RawResourceReader(private val application: Application) {
    private val lock = Any()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    fun readFromCreaturesFile(): List<Monster> {
        val monsterList: MutableList<Monster> = mutableListOf()
        val inputStream = application.resources
            .openRawResource(R.raw.heroes3)

        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val reader =
                    BufferedReader(InputStreamReader(inputStream))
                while (reader.ready()) {
                    synchronized(lock) {
                        val line = reader.readLine()
                            .substringBefore(" Gold")
                            .replace(" ", "\t")
                            .split("\t")
                        val monsterMapper = fromRawLineToMonsterMapper(line)
                        monsterList.add(FromMonsterMapperToMonster(monsterMapper).map())
                    }
                }
        }
    }
    return monsterList
}

private fun fromRawLineToMonsterMapper(listFormLine: List<String>): MonsterMapper {
    val rawMonster = MonsterMapper()
    for (i in 0..listFormLine.size) {
        when (i) {
            0 -> rawMonster.name = listFormLine[i]
            1 -> rawMonster.IMG_url = listFormLine[i]
            2 -> rawMonster.level = FromNumToLevel().invoke(listFormLine[i])
            3 -> rawMonster.attack = listFormLine[i].toInt()
            4 -> rawMonster.defence = listFormLine[i].toInt()
            5 -> rawMonster.damageFrom = listFormLine[i].toInt()
            6 -> rawMonster.damageTo = listFormLine[i].toInt()
            7 -> rawMonster.health = listFormLine[i].toInt()
            8 -> rawMonster.speed = listFormLine[i].toInt()
            9 -> rawMonster.growth = listFormLine[i].toInt()
            11 -> rawMonster.cost = listFormLine[i].toInt()
            else -> {}
        }
    }
    return rawMonster
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

}