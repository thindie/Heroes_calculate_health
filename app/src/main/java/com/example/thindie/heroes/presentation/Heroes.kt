package com.example.thindie.heroes.presentation


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
        val needToBeSmaller = rememberSaveable { mutableStateOf(false) }.apply { Log.d("EXPANDED", "Clcik")}

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

                    MonsterColumn(
                        viewModel,
                        currentMonsterList,
                        modifier = Modifier.heightIn(
                             max = if (needToBeSmaller.value) 560.dp.minus(180.dp) else 530.dp ,
                             min = if (needToBeSmaller.value) 360.dp.minus(180.dp) else 330.dp
                            )
                     )

                HeroesBottomBar(
                    viewModel = viewModel,
                    expandedInBottomBar = {boolean : Boolean -> needToBeSmaller.value = boolean },
                    modifier = modifier
                )
            }

        }

    }
}

