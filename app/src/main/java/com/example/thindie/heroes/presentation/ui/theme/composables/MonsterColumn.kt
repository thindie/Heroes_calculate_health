package com.example.thindie.heroes.presentation.ui.theme.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.CHECKED
import com.example.thindie.heroes.domain.EXPANDED
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.domain.SEARCH_BY_LEVEL

@SuppressLint("SuspiciousIndentation")
@Composable
fun MonsterColumn(
    viewModel: HeroesViewModel,
    currentMonsterList: State<List<Monster>?>,
    modifier: Modifier
) {
    val allMonstersList = viewModel.representAllMonsterList.observeAsState()


        LazyColumn(
            contentPadding = PaddingValues(horizontal = 1.dp),
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ) {
            items(currentMonsterList.value!!) { monster ->
                MonsterCard(
                    monster = monster,
                    checked = monster.checkedToCalculate.first,
                    expanded = monster.expandToDetailView.first,
                    onClickExpanded = {
                        viewModel.representUserBehavior(
                           EXPANDED,
                            monster,
                            currentMonsterList.value
                        )
                    },
                    onClickChecked = {
                        viewModel.representUserBehavior(
                            CHECKED,
                            monster,
                            currentMonsterList.value
                        )
                    },
                    onClickCoLevel = {
                        viewModel.representUserBehavior(
                            SEARCH_BY_LEVEL,
                            monster,
                        allMonstersList.value
                            )
                    }
                )
            }

        }
    }
