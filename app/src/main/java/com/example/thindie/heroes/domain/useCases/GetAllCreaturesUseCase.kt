package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster

class GetAllCreaturesUseCase(private val heroesRepository: HeroesRepository) {
    fun getAllCreatures() : List<Monster>{
        return heroesRepository.getAllCreatures()
    }
}