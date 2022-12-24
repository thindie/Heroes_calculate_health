package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Monster

class GetMonsterUseCase(private val heroesRepository: HeroesRepository) {
    fun getMonster(string: String) : Monster{
       return heroesRepository.acceptMonster(string)
    }
}