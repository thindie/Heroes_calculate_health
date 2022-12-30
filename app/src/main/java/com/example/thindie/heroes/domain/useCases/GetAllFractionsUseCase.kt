package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.FractionToImage

class GetAllFractionsUseCase(private val heroesRepository: HeroesRepository) {
    fun getAllFractions() : List<FractionToImage>{
        return heroesRepository.getAllFractions()
    }
}