package com.example.thindie.heroes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.heroes.data.HeroesRepositoryImpl
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.useCases.CalculateGrowthUseCase
import com.example.thindie.heroes.domain.useCases.GetAllCreaturesUseCase
import com.example.thindie.heroes.domain.useCases.GetFractionUseCase
import com.example.thindie.heroes.domain.useCases.GetMonsterUseCase

class HeroesViewModel(application: Application) : AndroidViewModel(application) {
    private val heroesRepository = HeroesRepositoryImpl(application)
    private val calculateGrowthUseCase = CalculateGrowthUseCase(heroesRepository)
    private val getFractionUseCase = GetFractionUseCase(heroesRepository)
    private val getMonsterUseCase = GetMonsterUseCase(heroesRepository)
    private val getAllCreaturesUseCase = GetAllCreaturesUseCase(heroesRepository)

    private val _monster = MutableLiveData<Monster>()
    val monster: LiveData<Monster>
        get() = _monster

    private val _allMonsters = MutableLiveData<List<Monster>>()
    val allMonsters: LiveData<List<Monster>>
        get() = _allMonsters

    private val _fraction = MutableLiveData<List<Monster>>()
    val fraction: LiveData<List<Monster>>
        get() = _fraction

    init {
        allMonsters()
    }

     fun singleMonster(string: String) {
        _monster.value = getMonsterUseCase.getMonster(string)
    }

    private fun allMonsters() {
        _allMonsters.value = getAllCreaturesUseCase.getAllCreatures()
    }

     fun getFraction(fraction: Fraction) {
        _fraction.value = getFractionUseCase.getFraction(fraction)
    }
}