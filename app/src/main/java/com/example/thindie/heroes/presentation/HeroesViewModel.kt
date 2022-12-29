package com.example.thindie.heroes.presentation

import android.annotation.SuppressLint
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
    private val accumulateGoldUseCase = AccumulateGoldUseCase(heroesRepository)

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

    private val _gold = MutableLiveData<Int>()
    val gold: LiveData<Int>
        get() = _gold

    init {
        allMonsters()
        getAllFractions()
    }

    fun setZeroGoldIndication(){
        _gold.value = 0
    }

    fun showHealth(week: Week?) {
        if(week == null){
            val chosenMonsters = collectAllCountableMonstersUseCase.collectCountable()
            val healthPoints = calculateGrowthUseCase.calculateGrowth(week = Week(), list = chosenMonsters)
            _healthPoints.value = healthPoints
        }
        else{
            val chosenMonsters = collectAllCountableMonstersUseCase.collectCountable()
            val healthPoints = calculateGrowthUseCase.calculateGrowth(week = week, list = chosenMonsters)
            _healthPoints.value = healthPoints
        }

       }

    fun showGold(list: List<Monster>, week: Week){
        _gold.value = accumulateGoldUseCase.accumulateGoldUseCase(list,week)
    }




    fun changeStatus(onChangedMonster: Monster, param: Pair<Boolean, String>, listToChange: List<Monster>) {
        val thinList = mutableListOf<Monster>()


        val name = onChangedMonster.name

        listToChange.forEach {
            thinList.add(
                it.apply {
                    if (this.name == name) {
                        when (param.second.trim().lowercase()) {
                            EXPANDED -> {
                                when (this.expandToDetailView.first) {
                                    true -> this.expandToDetailView = false to EXPANDED
                                    else -> this.expandToDetailView = true to EXPANDED
                                }
                            }
                            CHECKED -> {
                                when (this.checkedToCalculate.first) {
                                    true -> {
                                        this.checkedToCalculate = false to CHECKED; removeChecked(
                                            this.name
                                        )
                                    }
                                    else -> {
                                        this.checkedToCalculate =
                                            true to CHECKED; addChecked(this.name)
                                    }
                                }
                            }
                        }
                    }
                })
            _checkedMonsters.value = collectAllCountableMonstersUseCase.collectCountable()
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
                              val correctedList =  correctSingleMonsterList(searchingList);
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