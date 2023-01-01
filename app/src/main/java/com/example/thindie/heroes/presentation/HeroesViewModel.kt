package com.example.thindie.heroes.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.heroes.data.HeroesRepositoryImpl
import com.example.thindie.heroes.domain.CHECKED
import com.example.thindie.heroes.domain.EXPANDED
import com.example.thindie.heroes.domain.SEARCH_BY_LEVEL
import com.example.thindie.heroes.domain.entities.*
import com.example.thindie.heroes.domain.useCases.*

class HeroesViewModel(application: Application) : AndroidViewModel(application) {
    private val heroesRepository = HeroesRepositoryImpl(application)
    private val calculateGrowthUseCase = CalculateGrowthUseCase(heroesRepository)
    private val getFractionUseCase = GetFractionUseCase(heroesRepository)
    private val getAllFractionsUseCase = GetAllFractionsUseCase(heroesRepository)
    private val collectAllCountableMonstersUseCase =
        CollectAllCountableMonstersUseCase(heroesRepository)
    private val accumulateGoldUseCase = AccumulateGoldUseCase(heroesRepository)
    private val getAllCreaturesUseCase = GetAllCreaturesUseCase(heroesRepository)

    private val checkedMonsterList: MutableList<Monster> = mutableListOf()

    private val _healthPoints = MutableLiveData<HealthPoints>()
    val healthPoints: LiveData<HealthPoints>
        get() = _healthPoints

    private val _actualGoldCost = MutableLiveData<Int>()
    val actualGoldCost: LiveData<Int>
        get() = _actualGoldCost

    private val _representCurrentMonsterList = MutableLiveData<List<Monster>>()
    val representCurrentMonsterList: LiveData<List<Monster>>
        get() = _representCurrentMonsterList

    private val _representAllMonsterList = MutableLiveData<List<Monster>>()
    val representAllMonsterList: LiveData<List<Monster>>
        get() = _representAllMonsterList

    init {
        representTotalMonsterList()
    }

    private fun representTotalMonsterList() {
        _representAllMonsterList.value = getAllCreaturesUseCase.getAllCreatures()
        _representCurrentMonsterList.value = getAllCreaturesUseCase.getAllCreatures()
    }

    fun representFractionRow(): LiveData<List<FractionToImage>> {
        val dataToShow = MutableLiveData<List<FractionToImage>>()
        checkedMonsterList.clear()
        dataToShow.value = getAllFractionsUseCase.getAllFractions()
        return dataToShow
    }

    fun representFractionColumn(fraction: Fraction) {
        checkedMonsterList.clear()
        _representCurrentMonsterList.value = getFractionUseCase.getFraction(fraction).toList()

    }

    fun representCheckedMonsterList() {
        collectAllCountableMonstersUseCase.collectCountable(checkedMonsterList)
    }

    fun representUserBehavior(
        paramOrMonsterName: String,
        incomingMonster: Monster?,
        incomingList: List<Monster>?,
    ) {
        checkedMonsterList.clear()
        val searchingList: MutableList<Monster> = mutableListOf()
        when (paramOrMonsterName) {

            SEARCH_BY_LEVEL -> {
                incomingList?.forEach { monster ->
                    if (monster.level == incomingMonster?.level)
                        searchingList.add(monster)
                }
            }

            EXPANDED -> {
                incomingList?.forEach { monster ->
                    if (monster.name == incomingMonster?.name)
                        searchingList.add(
                            monster.copy(
                                expandToDetailView = !monster.expandToDetailView.first to EXPANDED,
                                fraction = monster.fraction
                            )
                        )
                    else {
                        searchingList.add(monster)
                    }
                }
            }

            CHECKED -> {
                checkedMonsterList.clear()
                incomingList?.forEach { monster ->
                    if (monster.name == incomingMonster?.name) {

                        val monsterToAdd = monster.copy(
                            checkedToCalculate = !monster.checkedToCalculate.first to CHECKED,
                            fraction = monster.fraction
                        )
                        checkedMonsterList.add(monsterToAdd)
                        searchingList.add(monsterToAdd)
                    } else {
                        searchingList.add(monster)
                        checkedMonsterList.add(monster)
                    }
                }
            }

            paramOrMonsterName -> {
                incomingList?.forEach { monster ->
                    if (monster.name.lowercase()
                            .contains(paramOrMonsterName.lowercase().trim())
                    )
                        searchingList.add(monster)
                }
            }

        }

        _representCurrentMonsterList.value = searchingList.toList()
        Log.d("SERVICE", "viewModel UserBehav")
        Log.d("SERVICE", checkedMonsterList.toString())
    }

    private fun representGoldToZero() {
        _actualGoldCost.value = 0
    }

    fun representCountedHealth(week: Week?) {
        if (week == null) {
            val chosenMonsters =
                collectAllCountableMonstersUseCase.collectCountable(checkedMonsterList)
            val healthPoints =
                calculateGrowthUseCase.calculateGrowth(week = Week(), list = chosenMonsters)
            _healthPoints.value = healthPoints
        } else {
            val chosenMonsters =
                collectAllCountableMonstersUseCase.collectCountable(checkedMonsterList)
            val healthPoints =
                calculateGrowthUseCase.calculateGrowth(week = week, list = chosenMonsters)
            _healthPoints.value = healthPoints
        }

    }

    fun representTotalGold(list: List<Monster>?, week: Week) {
        if (list == null) {
            representGoldToZero(); return
        } else {
            val listToCount = mutableListOf<Monster>()
            list.forEach { monster ->
                if (monster.checkedToCalculate.first) listToCount.add(monster)
            }
            _actualGoldCost.value = accumulateGoldUseCase.accumulateGoldUseCase(listToCount, week)
        }

    }

}
