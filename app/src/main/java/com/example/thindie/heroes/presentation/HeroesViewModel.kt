package com.example.thindie.heroes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.heroes.data.HeroesRepositoryImpl
import com.example.thindie.heroes.domain.entities.*
import com.example.thindie.heroes.domain.useCases.*

class HeroesViewModel(application: Application) : AndroidViewModel(application) {
    private val heroesRepository = HeroesRepositoryImpl(application)
    private val calculateGrowthUseCase = CalculateGrowthUseCase(heroesRepository)
    private val getFractionUseCase = GetFractionUseCase(heroesRepository)
    private val getMonsterUseCase = GetMonsterUseCase(heroesRepository)
    private val getAllCreaturesUseCase = GetAllCreaturesUseCase(heroesRepository)
    private val getAllFractionsUseCase = GetAllFractionsUseCase(heroesRepository)
    private val collectAllCountableMonstersUseCase = CollectAllCountableMonstersUseCase(heroesRepository)

    private val calculateHealthList: MutableList<Monster> = mutableListOf()


    private val _monster = MutableLiveData<Monster>()
    val monster: LiveData<Monster>
        get() = _monster

    private val _allMonsters = MutableLiveData<List<Monster>>()
    val allMonsters: LiveData<List<Monster>>
        get() = _allMonsters

    private val _fraction = MutableLiveData<List<Monster>>()
    val fraction: LiveData<List<Monster>>
        get() = _fraction

    private val _checkedMonsters = MutableLiveData<List<Monster>>()
    val  checkedMonsters: LiveData<List<Monster>>
        get() = _checkedMonsters

    private val _healthPoints = MutableLiveData<HealthPoints>()
    val   healthPoints: LiveData<HealthPoints>
        get() = _healthPoints

    private val _allFractions = MutableLiveData<List<FractionPair>>()
    val allFractions: LiveData<List<FractionPair>>
        get() = _allFractions

    init {
        allMonsters()
        getAllFractions()
    }

    fun showHealth() {
        val chosenMonsters = collectAllCountableMonstersUseCase.collectCountable()
        val healthPoints = calculateGrowthUseCase.calculateGrowth(week = Week(),
        list = chosenMonsters)

        _healthPoints.value = healthPoints
        _checkedMonsters.value = collectAllCountableMonstersUseCase.collectCountable()
    }

    fun changeStatus(name: String, param: Pair<Boolean, String>, listToChange: List<Monster>) {


        val thinList = mutableListOf<Monster>()
        listToChange.forEach {
            thinList.add(
                it.apply {
                    if (this.name == name) {
                        when (param.second.trim().lowercase()) {
                            "expanded" -> {
                                when (this.expandToDetailView.first) {
                                    true -> this.expandToDetailView = false to "expanded"
                                    else -> this.expandToDetailView = true to "expanded"
                                }
                            }
                            "checked" -> {
                                when (this.checkedToCalculate.first) {
                                    true -> {this.checkedToCalculate = false to "checked"; removeChecked(this.name)}
                                    else -> {this.checkedToCalculate = true to "checked"; addChecked(this.name)}
                                }
                            }
                        }
                    }
                })
            _allMonsters.value = thinList.toList()
            _fraction.value = thinList.toList()
        }
    }

    fun searchEngine(string: String, incomingMonster: Monster?) {
                    calculateHealthList.clear()
                    val searchingList: MutableList<Monster> = mutableListOf()
                    val monsterList = getAllCreaturesUseCase.getAllCreatures()
                    when (string) {
                        SEARCH_BY_LEVEL -> {
                            monsterList.forEach { monster ->
                                if (monster.level == incomingMonster!!.level)
                                    searchingList.add(monster)
                            }
                            _allMonsters.value = searchingList
                            _fraction.value = searchingList
                        }
                        else -> {
                            monsterList.forEach { monster ->
                                if (monster.name.lowercase().contains(string.lowercase().trim()))
                                    searchingList.add(monster)
                            }
                            if(searchingList.size == 1){
                              val correctedList =  correctSingleMonsterList(searchingList)
                                searchingList.clear()
                                searchingList.addAll(correctedList)
                            }
                            _allMonsters.value = searchingList
                            _fraction.value = searchingList
                        }
                    }


                }

                private fun allMonsters() {
                    calculateHealthList.clear()
                    _allMonsters.value = (getAllCreaturesUseCase.getAllCreatures())
                }

                fun getFraction(fraction: Fraction) {
                    calculateHealthList.clear()
                    _fraction.value = (getFractionUseCase.getFraction(fraction))
                }

                private fun addChecked(string: String) {
                    calculateHealthList.add(getMonsterUseCase.getMonster(string))
                }

                private fun removeChecked(string: String){
                    calculateHealthList.remove(getMonsterUseCase.getMonster(string))
                }


                private fun correctSingleMonsterList(list: MutableList<Monster>) : MutableList<Monster>{
                    val resultList = mutableListOf<Monster>()
                    val mon = list[0]
                    val fraction = getFractionUseCase.getFraction(mon.fraction)
                    fraction.forEach { if(mon.level == it.level){resultList.add(it)} }
                    return resultList
                }

                private fun getAllFractions() {
                    calculateHealthList.clear()
                    _allFractions.value = getAllFractionsUseCase.getAllFractions()
                }
        }