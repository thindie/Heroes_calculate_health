package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.CHECKED
import com.example.thindie.heroes.presentation.HeroesViewModel

@Composable
fun HeroesControlTab(viewModel: HeroesViewModel){
    val checkedMonsters = viewModel.representCurrentMonsterList


    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Column() {
            Text(text = "Set Week")
        }
        Spacer(modifier = Modifier.weight(0.3f))
        Column() {
            Text(text = "..Growth")
        }
        Spacer(modifier = Modifier.weight(0.3f))
        Column(
            modifier = Modifier.
                    clickable {
                        val list = checkedMonsters.value
                        if(!list.isNullOrEmpty()){
                            list.forEach {monster ->
                                viewModel.representUserBehavior(CHECKED,  monster, list.toList())
                            }
                            viewModel.representUserBehavior("",
                                    null,
                                   checkedMonsters.value)
                        }

                          })
         {
            Text(text = "Uncheck",
                modifier = Modifier
                    .border(border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.onBackground),
                    shape = ShapeDefaults.Small)

            )
        }
    }
}

