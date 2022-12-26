package com.example.thindie.heroes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.heroes.data.HeroesRepositoryImpl
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.FractionPair
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.useCases.*

class HeroesViewModel(application: Application) : AndroidViewModel(application) {
    private val heroesRepository = HeroesRepositoryImpl(application)
    private val calculateGrowthUseCase = CalculateGrowthUseCase(heroesRepository)
    private val getFractionUseCase = GetFractionUseCase(heroesRepository)
    private val getMonsterUseCase = GetMonsterUseCase(heroesRepository)
    private val getAllCreaturesUseCase = GetAllCreaturesUseCase(heroesRepository)
    private val getAllFractionsUseCase = GetAllFractionsUseCase(heroesRepository)

    private val _monster = MutableLiveData<Monster>()
    val monster: LiveData<Monster>
        get() = _monster

    private val _allMonsters = MutableLiveData<List<Monster>>()
    val allMonsters: LiveData<List<Monster>>
        get() = _allMonsters

    private val _fraction = MutableLiveData<List<Monster>>()
    val fraction: LiveData<List<Monster>>
        get() = _fraction

    private val _allFractions = MutableLiveData<List<FractionPair>>()
    val allFractions: LiveData<List<FractionPair>>
        get() = _allFractions

    init {
        allMonsters()
        getAllFractions()
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

    private fun getAllFractions() {
        _allFractions.value = getAllFractionsUseCase.getAllFractions()
    }
}