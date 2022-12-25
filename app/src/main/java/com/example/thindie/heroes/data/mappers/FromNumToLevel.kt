package com.example.thindie.heroes.data.mappers

import com.example.thindie.heroes.domain.entities.MonsterLevel


class FromNumToLevel {
    fun invoke(int: String): MonsterLevel {
        when (int) {
            "1" -> return MonsterLevel.ONE
            "2" -> return MonsterLevel.TWO
            "3" -> return MonsterLevel.THREE
            "4" -> return MonsterLevel.FOUR
            "5" -> return MonsterLevel.FIVE
            "6" -> return MonsterLevel.SIX
            "7" -> return MonsterLevel.SEVEN
            "1+" -> return MonsterLevel.ONE
            "2+" -> return MonsterLevel.TWO
            "3+" -> return MonsterLevel.THREE
            "4+" -> return MonsterLevel.FOUR
            "5+" -> return MonsterLevel.FIVE
            "6+" -> return MonsterLevel.SIX
            "7+" -> return MonsterLevel.SEVEN
            "1+*" -> return MonsterLevel.ONE
            "2+*" -> return MonsterLevel.TWO
            "3+*" -> return MonsterLevel.THREE
            "4+*" -> return MonsterLevel.FOUR
            "5+*" -> return MonsterLevel.FIVE
            "6+*" -> return MonsterLevel.SIX
            "7+*" -> return MonsterLevel.SEVEN
        }
        return MonsterLevel.ONE
    }
}