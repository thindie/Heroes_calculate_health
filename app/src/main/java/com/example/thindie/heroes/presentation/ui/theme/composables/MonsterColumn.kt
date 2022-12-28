package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
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


    val checked = viewModel.monster.observeAsState()

    val expanded = viewModel.monster.observeAsState()


    Surface(
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = modifier
            .heightIn(min = 300.dp, max = 600.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier.fillMaxHeight(),

            ) {
            items(list.value!!) { monster ->
                MonsterCard(
                    monster = monster,
                    modifier = modifier,
                    checked = monster.checkedToCalculate.first,
                    expanded = monster.expandToDetailView.first,
                    onClickExpanded = {
                        viewModel.changeStatus(
                            monster.name,
                            monster.expandToDetailView,
                            list.value!!
                        )
                    },
                    onClickChecked = {
                        viewModel.changeStatus(
                            monster.name,
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

}