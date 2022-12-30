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


    private fun representList(incoming: List<Monster>) : LiveData<List<Monster>>{
        val dataToShow = MutableLiveData<List<Monster>>()
        dataToShow.value = incoming
        return dataToShow
    }

    fun representTotalMonsterList() : LiveData<List<Monster>> {
       return representList(getAllCreaturesUseCase.getAllCreatures())
    }

    fun representFractionRow() : LiveData<List<FractionToImage>> {
        val dataToShow = MutableLiveData<List<FractionToImage>>()
        checkedMonsterList.clear()
        dataToShow.value = getAllFractionsUseCase.getAllFractions()
        return dataToShow
    }

    fun representFractionColumn(fraction: Fraction) : LiveData<List<Monster>> {
        checkedMonsterList.clear()
        fractionList.clear()
        fractionList.addAll(getFractionUseCase.getFraction(fraction))
        return representUserBehavior(SEARCH_BY_FRACTION,null, fractionList)
    }

    fun representCheckedMonster() : LiveData<List<Monster>> {
        return representList(checkedMonsterList)
    }

    fun representUserBehavior(string: String,
                     incomingMonster: Monster?,
                     incomingList: List<Monster>?)
            : LiveData<List<Monster>> {
        checkedMonsterList.clear()
        val searchingList: MutableList<Monster> = mutableListOf()
        when (string) {

            SEARCH_BY_LEVEL -> {
                allMonsterList.forEach { monster ->
                    if (monster.level == incomingMonster?.level)
                        searchingList.add(monster)
                }
            }

            EXPANDED -> {
                incomingList!!.forEach { monster ->
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
                incomingList!!.forEach { monster ->
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

            else -> {
                allMonsterList.forEach { monster ->
                    if (monster.name.lowercase()
                            .contains(string.lowercase().trim())
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
        return representList(searchingList)
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
