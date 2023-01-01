package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Week
import com.example.thindie.heroes.presentation.HeroesViewModel

@Composable
fun HeroesControlTab(viewModel: HeroesViewModel) {
    val resetMonsterList = viewModel.representAllMonsterList.observeAsState()

    Row(modifier = Modifier
        .padding(top = 20.dp, bottom = 20.dp, start = 20.dp)
        .height(20.dp)) {
        Column(
            modifier = Modifier.clickable {
                viewModel.representUserBehavior(
                    "",
                    null,
                    resetMonsterList.value
                )
                viewModel.representCheckedMonsterList()
                viewModel.representCountedHealth(Week(weekNumber = Week().weekNumber))
                viewModel.representTotalGold(
                   null,
                    Week())
            }
        ) {
            Text(
                text = "   reset   ",
                modifier = Modifier
                    .border(
                        border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.onBackground),
                        shape = ShapeDefaults.Small
                    )

            )
        }
        Spacer(modifier = Modifier.weight(0.3f))
        Column() {
            Text(text = "")
        }
        Spacer(modifier = Modifier.weight(0.3f))
        Column() {
            Text(text = " ")
        }


    }
}

