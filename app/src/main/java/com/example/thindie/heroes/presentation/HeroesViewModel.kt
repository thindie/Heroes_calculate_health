package com.example.thindie.heroes.presentation

import androidx.lifecycle.ViewModel
import com.example.thindie.heroes.data.HeroesRepositoryImpl
import com.example.thindie.heroes.domain.useCases.CalculateGrowthUseCase
import com.example.thindie.heroes.domain.useCases.GetFractionUseCase
import com.example.thindie.heroes.domain.useCases.GetMonsterUseCase

class HeroesViewModel : ViewModel() {
    private val heroesRepository = HeroesRepositoryImpl()
    private val calculateGrowthUseCase = CalculateGrowthUseCase(heroesRepository)
    private val getFractionUseCase = GetFractionUseCase(heroesRepository)
    private val getMonsterUseCase = GetMonsterUseCase(heroesRepository)


}