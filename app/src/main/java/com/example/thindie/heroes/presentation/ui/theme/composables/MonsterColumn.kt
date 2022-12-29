package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.SEARCH_BY_LEVEL

@Composable
fun MonsterColumn(
    modifier: Modifier = Modifier,
    viewModel: HeroesViewModel,
    list: State<List<Monster>?>
) {


        LazyColumn(
            contentPadding = PaddingValues(horizontal = 1.dp),
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
            ) {
            items(list.value!!) { monster ->
                MonsterCard(
                    monster = monster,
                    modifier = Modifier,
                    checked = monster.checkedToCalculate.first,
                    expanded = monster.expandToDetailView.first,
                    onClickExpanded = {
                        viewModel.changeStatus(
                            onChangedMonster =  monster ,
                            monster.expandToDetailView,
                            list.value!!
                        )
                    },
                    onClickChecked = {
                        viewModel.changeStatus(
                            onChangedMonster =  monster ,
                            monster.checkedToCalculate,
                            list.value!!
                        )
                    },
                    onClickCoLevel = {
                        viewModel.searchEngine(SEARCH_BY_LEVEL, monster)
                    }
                )
            }

        }
    }
