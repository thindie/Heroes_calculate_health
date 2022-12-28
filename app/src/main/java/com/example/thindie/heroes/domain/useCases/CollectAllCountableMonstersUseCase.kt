package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster

class CollectAllCountableMonstersUseCase (private val heroesRepository: HeroesRepository) {
    fun collectCountable() : List<Monster>{
        return heroesRepository.collectAllCountableMonsters()
    }
}