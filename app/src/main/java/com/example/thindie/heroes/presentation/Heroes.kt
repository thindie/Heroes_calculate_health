package com.example.thindie.heroes.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.MonsterLevel
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme
import com.example.thindie.heroes.presentation.ui.theme.composables.FractionRow
import com.example.thindie.heroes.presentation.ui.theme.composables.HeroesSearchBar
import com.example.thindie.heroes.presentation.ui.theme.composables.MonsterColumn


@Composable
fun Heroes(viewModel: HeroesViewModel) {
    HeroesTheme {
        val creatures = viewModel.allMonsters.observeAsState()

        Column {
            HeroesSearchBar()
            FractionRow(onClick = {})
            MonsterColumn(
                modifier = Modifier,
                creatures
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



fun fractionList(): List<Fraction> {

    return mutableListOf(
        Fraction.INFERNO,
        Fraction.RAMPART,
        Fraction.FORTRESS,
        Fraction.CASTLE,
        Fraction.CITADEL,
        Fraction.CONFLUX,
        Fraction.TOWER
    )
}
