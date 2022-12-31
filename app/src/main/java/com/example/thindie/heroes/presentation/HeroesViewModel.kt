package com.example.thindie.heroes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.heroes.data.HeroesRepositoryImpl
import com.example.thindie.heroes.domain.CHECKED
import com.example.thindie.heroes.domain.EXPANDED
import com.example.thindie.heroes.domain.SEARCH_BY_FRACTION
import com.example.thindie.heroes.domain.SEARCH_BY_LEVEL
import com.example.thindie.heroes.domain.entities.*
import com.example.thindie.heroes.domain.useCases.*

class HeroesViewModel(application: Application) : AndroidViewModel(application) {
    private val heroesRepository = HeroesRepositoryImpl(application)
    private val calculateGrowthUseCase = CalculateGrowthUseCase(heroesRepository)
    private val getFractionUseCase = GetFractionUseCase(heroesRepository)
    private val getMonsterUseCase = GetMonsterUseCase(heroesRepository)
    private val getAllFractionsUseCase = GetAllFractionsUseCase(heroesRepository)
    private val collectAllCountableMonstersUseCase = CollectAllCountableMonstersUseCase(heroesRepository)
    private val accumulateGoldUseCase = AccumulateGoldUseCase(heroesRepository)
    private val getAllCreaturesUseCase = GetAllCreaturesUseCase(heroesRepository)

    private val checkedMonsterList: MutableList<Monster> = mutableListOf()
    private val fractionList: MutableList<Monster> = mutableListOf()
    private val allMonsterList: MutableList<Monster> = mutableListOf()

    private val _healthPoints = MutableLiveData<HealthPoints>()
    val   healthPoints: LiveData<HealthPoints>
        get() = _healthPoints

    private val _representCurrentMonsterList = MutableLiveData<List<Monster>>()
    val    representCurrentMonsterList: LiveData<List<Monster>>
        get() = _representCurrentMonsterList

    init {
        representTotalMonsterList()

    }

    private fun representTotalMonsterList()   {
       _representCurrentMonsterList.value =  getAllCreaturesUseCase.getAllCreatures() .toList()
    }

    fun representFractionRow() : LiveData<List<FractionToImage>> {
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
       _representCurrentMonsterList.value = checkedMonsterList.toList()
    }

    fun representUserBehavior(paramOrMonsterName: String,
                             incomingMonster: Monster?,
                             incomingList: List<Monster>?,)
    {
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
                                expandToDetailView = !monster.expandToDetailView.first to EXPANDED
                            )
                        )
                    else {
                        searchingList.add(monster)
                    }
                }
            }

            CHECKED -> {
                incomingList?.forEach { monster ->
                    if (monster.name == incomingMonster?.name)
                        searchingList.add(
                            monster.copy(
                                checkedToCalculate = !monster.checkedToCalculate.first to CHECKED
                            )
                        )
                    else {
                        searchingList.add(monster)
                    }
                }
            }

            SEARCH_BY_FRACTION -> {
                searchingList.addAll(fractionList)
            }

            paramOrMonsterName -> {
                 incomingList?.forEach { monster ->
                    if (monster.name.lowercase()
                            .contains(paramOrMonsterName.lowercase().trim())
                    )
                        searchingList.add(monster)
                }
                if (searchingList.size == 1) {
                    val correctedList = correctSingleMonsterList(searchingList);
                    searchingList.clear()
                    searchingList.addAll(correctedList)
                }

            }

        }
        _representCurrentMonsterList.value = searchingList.toList()
    }

    fun representGoldToZero(): MutableLiveData<Int> {
        val dataToShow = MutableLiveData<Int>()
        dataToShow.value = 0
        return dataToShow
    }

    fun representCountedHealth(week: Week?) {
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

    fun representTotalGold(list: List<Monster>, week: Week): MutableLiveData<Int> {
        val dataToShow = MutableLiveData<Int>()
        dataToShow.value = accumulateGoldUseCase.accumulateGoldUseCase(list,week)
        return   dataToShow
    }

    private fun addCheckedMonsterToObserveList(string: String) {
        checkedMonsterList.add(getMonsterUseCase.getMonster(string))
    }

    private fun removeCheckedMonsterFromObserveList(string: String){
        checkedMonsterList.remove(getMonsterUseCase.getMonster(string))
    }

    private fun correctSingleMonsterList(list: MutableList<Monster>) : MutableList<Monster>{
        val resultList = mutableListOf<Monster>()
        val mon = list[0]
        val fraction = getFractionUseCase.getFraction(mon.fraction)
        fraction.forEach { if(mon.level == it.level){resultList.add(it)} }
        return resultList
    }

}
