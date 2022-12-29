package com.example.thindie.heroes.presentation.ui.theme.composables


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.HeroesViewModel

@Composable
fun HeroesBottomBar(viewModel: HeroesViewModel, modifier: Modifier) {
    val healthPoints = viewModel.healthPoints.observeAsState()
    val collectedData = remember { mutableStateOf(false) }
    val expanded = rememberSaveable {
        mutableStateOf(false)
    }

    val additionPadding by animateDpAsState(
        targetValue =
        if (expanded.value) {
            220.dp
        } else {
           50.dp
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )


Surface(
    Modifier
        .height(additionPadding)
        .clip(ShapeDefaults.ExtraLarge)) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxHeight()
            .clickable {
                if(!expanded.value){expanded.value = !expanded.value}
                collectedData.value = !collectedData.value
                viewModel.showHealth();
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
                Text(text = healthPoints.value!!.health.toString())
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



    }
}


}



