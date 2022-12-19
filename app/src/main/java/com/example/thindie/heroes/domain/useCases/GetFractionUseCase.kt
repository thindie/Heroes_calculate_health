package com.example.thindie.heroes.domain.useCases

import com.example.thindie.heroes.domain.HeroesRepository
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster

class GetFractionUseCase(private val heroesRepository: HeroesRepository) {
    fun getFraction(fraction: Fraction) : List<Monster>{
        return heroesRepository.getFraction(fraction = fraction)
    }
}