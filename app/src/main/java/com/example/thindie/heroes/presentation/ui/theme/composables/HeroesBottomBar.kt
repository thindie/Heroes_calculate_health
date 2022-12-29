package com.example.thindie.heroes.presentation.ui.theme.composables


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Week
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.START_WEEK_VALUE
import com.example.thindie.heroes.presentation.USELESS_WEEK_VALUE

@Composable
fun HeroesBottomBar(viewModel: HeroesViewModel, modifier: Modifier) {
    val healthPoints = viewModel.healthPoints.observeAsState()
    val checkedMonsters = viewModel.checkedMonsters.observeAsState()
    val goldToPay = viewModel.gold.observeAsState()

    val collectedData = remember { mutableStateOf(false) }
    val expanded = rememberSaveable {
        mutableStateOf(false)
    }

    val week = rememberSaveable  {
        mutableStateOf(1)
    }

    val additionPadding by animateDpAsState(
        targetValue =
        if (expanded.value) {
            320.dp
        } else {
           150.dp
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    
    val changingColorIn = animateColorAsState(targetValue = MaterialTheme.colorScheme.inverseOnSurface
        )

    val changingColorOut = animateColorAsState(targetValue = MaterialTheme.colorScheme.surface
    )


Surface(
    color = if(expanded.value) changingColorIn.value else changingColorOut.value,
    modifier = Modifier
        .height(additionPadding)
        ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxHeight()
            .clickable {
                if (!expanded.value) {
                    expanded.value = !expanded.value
                }
                collectedData.value = !collectedData.value
                viewModel.showHealth(Week(week.value))
                if (checkedMonsters.value != null && !checkedMonsters.value.isNullOrEmpty()) {
                    viewModel.showGold(checkedMonsters.value!!, Week(week.value))
                }
                else(viewModel.setZeroGoldIndication())

                collectedData.value = true
            }
    ) {
        Spacer(modifier = modifier.weight(0.3f))
        Text(
            text = "Count Health",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(top = 1.dp, bottom = 2.dp)
                .align(if (expanded.value) Alignment.Top else Alignment.CenterVertically)
        )
        Spacer(modifier = modifier.weight(0.3f))
    }
    if (collectedData.value && expanded.value) {

        Row(){

            Column(modifier = Modifier
                .padding(top = 40.dp, start = 40.dp)) {

            }

            IconButton(
                onClick = { if(expanded.value){expanded.value = !expanded.value}},
                modifier = modifier.padding(start = 300.dp, top =  25.dp)
            ) {
                Icon(
                    imageVector =  Icons.Filled.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier.scale(0.7f)
                )
            }
        }
        Row(){
            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(top = 60.dp, start = 20.dp))
            {
                Text(text = "Accumulated HealthPoints : ".plus(healthPoints.value!!.health.toString()),
                        style = MaterialTheme.typography.titleSmall
                    )
                Text(text = "Week count : ".plus(week.value),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(text = "Gold to Pay : ".plus(goldToPay.value),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(top = 60.dp, end = 3.dp))
            {
                Spacer(modifier = Modifier.weight(.6f))
                Button(onClick =
                {
                    week.value++
                    if(week.value == USELESS_WEEK_VALUE){week.value = START_WEEK_VALUE}
                         }) {
                    Text(text = "Add week")
                }
                Spacer(modifier = Modifier.weight(.3f))
            }
        }



    }
}


}



