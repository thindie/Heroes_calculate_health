package com.example.thindie.heroes.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme
import com.example.thindie.heroes.presentation.ui.theme.composables.FractionRow
import com.example.thindie.heroes.presentation.ui.theme.composables.HeroesSearchBar
import com.example.thindie.heroes.presentation.ui.theme.composables.MonsterColumn


@Composable
fun Heroes(viewModel: HeroesViewModel) {

    HeroesTheme {
        val creatures = viewModel.allMonsters.observeAsState()
        val fractions = viewModel.allFractions.observeAsState()
        val fractionCreatures = viewModel.fraction.observeAsState()

        val actualValue = if (fractionCreatures.value == null) {
            creatures
        } else (fractionCreatures)

        Column {
            HeroesSearchBar()
            FractionRow(
                fractions,
                viewModel
            )
            MonsterColumn(
                modifier = Modifier,
                actualValue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesPreview() {
    HeroesTheme {

    }
}

