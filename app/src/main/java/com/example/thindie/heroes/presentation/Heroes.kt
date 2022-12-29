package com.example.thindie.heroes.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme
import com.example.thindie.heroes.presentation.ui.theme.composables.*


@SuppressLint("SuspiciousIndentation")
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

        Surface(
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onBackground,
            modifier = modifier

        ) {
            Spacer(modifier = modifier.height(2.dp))
            Column {
                HeroesSearchBar(viewModel, modifier)
                FractionRow(
                    fractions,
                    viewModel,
                    modifier = modifier.
                            clip(ShapeDefaults.ExtraLarge)
                )
                HeroesControlTab(modifier = modifier, viewModel = viewModel)
                MonsterColumn(
                    modifier = Modifier.weight(.8f),
                    viewModel,
                    actualValue,
                )
                Spacer(modifier = modifier.height(2.dp))
                HeroesBottomBar(viewModel = viewModel, modifier = Modifier.weight(.1f) )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun HeroesPreview() {
    HeroesTheme {

    }
}

