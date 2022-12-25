package com.example.thindie.heroes.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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


}

@Preview(showBackground = true)
@Composable
fun HeroesPreview() {
    HeroesTheme {
        Column {
            HeroesSearchBar()
            FractionRow(onClick = {})
            MonsterColumn(
                modifier = Modifier,
                monsterList()
            )
        }

    }
}

fun monsterList(): List<Monster> {
    val list: MutableList<Monster> = mutableListOf()
    for (i in 1..10) {
        list.add(
            Monster(
                "monster $i", i, i, i, i, i, i, i, i, Fraction.INFERNO, MonsterLevel.SIX, ""

            )
        )
    }
    return list
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
