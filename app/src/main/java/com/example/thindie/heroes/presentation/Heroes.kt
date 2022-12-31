package com.example.thindie.heroes.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Fraction
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
                    modifier = modifier.
                            clip(ShapeDefaults.ExtraLarge)
                )
                HeroesControlTab( viewModel = viewModel)

                MonsterColumn(
                    viewModel,
                    currentMonsterList,
                    modifier = modifier.fillMaxWidth()
                        .fillMaxHeight(0.7f)
                )
                HeroesBottomBar(viewModel = viewModel,
                modifier = modifier.fillMaxHeight())
            }

        }

    }
}

