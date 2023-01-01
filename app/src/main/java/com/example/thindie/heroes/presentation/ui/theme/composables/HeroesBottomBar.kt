package com.example.thindie.heroes.presentation.ui.theme.composables


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.START_WEEK_VALUE
import com.example.thindie.heroes.domain.USELESS_WEEK_VALUE
import com.example.thindie.heroes.domain.entities.Week
import com.example.thindie.heroes.presentation.HeroesViewModel


@Composable
fun HeroesBottomBar(
    viewModel: HeroesViewModel,
    expandedInBottomBar: (Boolean) -> Unit,
    modifier: Modifier
) {

    val expanded = rememberSaveable {
        mutableStateOf(false)
    }


    val weekNumber = rememberSaveable {
        mutableStateOf(1)
    }
    val healthPoints = viewModel.healthPoints.observeAsState()
    val checkedMonsters = viewModel.representCurrentMonsterList.observeAsState()
    val goldToPay = viewModel.actualGoldCost.observeAsState()


    val additionPadding by animateDpAsState(
        targetValue =
        if (expanded.value) {
            250.dp
        } else {
            40.dp
        },

        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val changingColorIn = animateColorAsState(
        targetValue = MaterialTheme.colorScheme.inverseOnSurface
    )

    val changingColorOut = animateColorAsState(
        targetValue = MaterialTheme.colorScheme.surface
    )
    Surface(
        color = if (expanded.value) changingColorIn.value else changingColorOut.value,
        modifier = modifier
            .requiredHeightIn(120.dp, max = additionPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier

        ) {

            Spacer(modifier = Modifier.weight(0.3f))
            if (!expanded.value) {
                Text(
                    text = "Count Health",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(top = 1.dp, bottom = 2.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            viewModel.representCheckedMonsterList()
                            viewModel.representCountedHealth(Week(weekNumber = weekNumber.value))
                            viewModel.representTotalGold(
                                checkedMonsters.value!!,
                                Week(weekNumber.value)
                            )
                            expanded.value = !expanded.value
                            expandedInBottomBar(expanded.value)

                        }
                )
            }

            Spacer(modifier = Modifier.weight(0.3f))
        }
        if (expanded.value) {
            Row {
                Column(
                    modifier = Modifier
                        .padding(top = 40.dp, start = 40.dp)
                ) {
                }
                IconButton(
                    onClick = {
                        if (expanded.value) {
                            expanded.value = !expanded.value
                            expandedInBottomBar(expanded.value)
                        }
                    },
                    modifier = Modifier.padding(start = 300.dp, top = 25.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.7f)
                    )
                }
            }
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 20.dp, start = 20.dp)
                )
                {
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Accumulated HealthPoints : ".plus(healthPoints.value!!.health.toString()),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Week count : ".plus(weekNumber.value),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = modifier.height(10.dp))

                    Text(
                        text = "Gold to Pay : ".plus(goldToPay.value),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = modifier.height(10.dp))

                    Row(Modifier.fillMaxWidth()) {
                        Button(
                            onClick =
                            {
                                viewModel.representCheckedMonsterList()
                                viewModel.representCountedHealth(Week(weekNumber = weekNumber.value))
                                viewModel.representTotalGold(
                                    checkedMonsters.value!!,
                                    Week(weekNumber.value)
                                )

                            },
                            modifier = modifier.padding(start = 5.dp)
                        ) {
                            Text(text = "Calculate")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick =
                            {
                                weekNumber.value++
                                if (weekNumber.value == USELESS_WEEK_VALUE) {
                                    weekNumber.value = START_WEEK_VALUE
                                }
                            },
                            modifier = modifier.padding(end = 5.dp)
                        ) {
                            Text(text = "Add week")
                        }
                    }

                }


            }
        }
    }
}









