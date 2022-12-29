package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.Week

class AccumulateGoldUseCase(private val heroesRepository: HeroesRepository) {
    fun accumulateGoldUseCase(monster : List<Monster>, week: Week): Int {
        return heroesRepository.accumulateGold(monster, week)
    }

}