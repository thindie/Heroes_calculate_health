package com.example.thindie.heroes.presentation.ui.theme.composables


import android.util.Log
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.HeroesViewModel

@Composable
fun HeroesButtonBar(viewModel: HeroesViewModel, modifier: Modifier) {
    val healthPoints = viewModel.healthPoints.observeAsState()
    val collectedData = remember{ mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(min = 50.dp, max = 60.dp)
            .clip(ShapeDefaults.ExtraLarge)
            .clickable {
                viewModel.showHealth(); collectedData.value = true
            }
    ) {
        Spacer(modifier = modifier.weight(0.3f))
        Text(
            text = "Count Health",
            style = MaterialTheme.typography.titleSmall
         )
        Spacer(modifier = modifier.weight(0.3f))

        if(collectedData.value){
            Text(text = healthPoints.value!!.health.toString())
            Log.d("SRVC_TG", healthPoints.value!!.health.toString())
        }

    }



}



