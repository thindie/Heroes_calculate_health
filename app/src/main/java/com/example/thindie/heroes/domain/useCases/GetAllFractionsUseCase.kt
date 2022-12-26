package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.FractionPair
import com.example.thindie.heroes.domain.entities.Monster

class GetAllFractionsUseCase(private val heroesRepository: HeroesRepository) {
    fun getAllFractions() : List<FractionPair>{
        return heroesRepository.getAllFractions()
    }
}