package com.example.thindie.heroes.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme
import com.example.thindie.heroes.presentation.ui.theme.composables.FractionRow
import com.example.thindie.heroes.presentation.ui.theme.composables.HeroesSearchBar
import com.example.thindie.heroes.presentation.ui.theme.composables.MonsterColumn


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Heroes(
    viewModel: HeroesViewModel,
    modifier: Modifier
) {

    HeroesTheme(

    ) {
        val creatures = viewModel.allMonsters.observeAsState()
        val fractions = viewModel.allFractions.observeAsState()
        val fractionCreatures = viewModel.fraction.observeAsState()

        val actualValue = if (fractionCreatures.value == null) {
            creatures
        } else (fractionCreatures)
        Spacer(modifier = modifier.height(16.dp))
        Column {
            HeroesSearchBar()
            FractionRow(
                fractions,
                viewModel,
                modifier = modifier
            )
            MonsterColumn(
                modifier = Modifier,
                actualValue
            )
        }
        Spacer(modifier = modifier.height(16.dp))


    }
}

@Preview(showBackground = true)
@Composable
fun HeroesPreview() {
    HeroesTheme {

    }
}

