package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.CHECKED
import com.example.thindie.heroes.domain.EXPANDED
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.domain.SEARCH_BY_LEVEL

@Composable
fun MonsterColumn(
    viewModel: HeroesViewModel,
    list: State<List<Monster>?>
) {


        LazyColumn(
            contentPadding = PaddingValues(horizontal = 1.dp),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
            ) {
            items(list.value!!) { monster ->
                MonsterCard(
                    monster = monster,
                    checked = monster.checkedToCalculate.first,
                    expanded = monster.expandToDetailView.first,
                    onClickExpanded = {
                        viewModel.representUserBehavior(
                           EXPANDED,
                            monster,
                            list.value
                        )
                    },
                    onClickChecked = {
                        viewModel.representUserBehavior(
                            CHECKED,
                            monster,
                            list.value
                        )
                    },
                    onClickCoLevel = {
                        viewModel.representUserBehavior(
                            SEARCH_BY_LEVEL,
                            monster,
                        list.value
                            )
                    }
                )
            }

        }
    }
