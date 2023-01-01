package com.example.thindie.heroes.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme
import com.example.thindie.heroes.presentation.ui.theme.composables.*


@SuppressLint("SuspiciousIndentation")
@Composable
fun Heroes(
    viewModel: HeroesViewModel,
    modifier: Modifier
) {

    HeroesTheme {
        val showFractionsRow = viewModel.representFractionRow().observeAsState()
        val currentMonsterList = viewModel.representCurrentMonsterList.observeAsState()
        val needToBeSmaller = rememberSaveable { mutableStateOf(false) }
        Surface(
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onBackground,

            ) {
            Spacer(modifier = modifier.height(2.dp))

            Column {
                HeroesSearchBar(viewModel)
                FractionRow(
                    showFractionsRow,
                    viewModel,
                    modifier = modifier.clip(ShapeDefaults.ExtraLarge)

                )
                HeroesControlTab(viewModel = viewModel)

                val expander = if (needToBeSmaller.value) {
                    Modifier.padding(bottom = 200.dp)
                } else {
                    Modifier.fillMaxHeight(0.95f)
                }
                MonsterColumn(
                    viewModel,
                    currentMonsterList,
                    modifier = expander
                )

                HeroesBottomBar(
                    viewModel = viewModel,
                    expandedInBottomBar = { boolean: Boolean -> needToBeSmaller.value = boolean },
                    modifier = modifier
                )
            }

        }

    }
}

