package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.CHECKED
import com.example.thindie.heroes.presentation.HeroesViewModel

@Composable
fun HeroesControlTab(modifier: Modifier, viewModel: HeroesViewModel){
    val checkedMonsters = viewModel.checkedMonsters.observeAsState()
    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Column() {
            Text(text = "Set Week")
        }
        Spacer(modifier = modifier.weight(0.3f))
        Column() {
            Text(text = "..Growth")
        }
        Spacer(modifier = modifier.weight(0.3f))
        Column(
            modifier = modifier.
                    clickable {
                        val list = checkedMonsters.value!!
                        if(list.isNotEmpty()){
                            list.forEach {
                                viewModel.changeStatus(it, true to CHECKED, list)
                            }
                        }
                          })
         {
            Text(text = "Uncheck")
        }
    }
}

