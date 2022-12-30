package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme

@Composable
fun MonsterCard(
    monster: Monster,
    expanded: Boolean,
    onClickExpanded: ()-> Unit,
    checked: Boolean,
    onClickChecked: (Boolean)-> Unit,
    onClickCoLevel: () -> Unit

) {


    val additionPadding by animateDpAsState(
        targetValue =
        if (expanded) {
            120.dp
        } else {
            0.dp
        },

        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )



    Surface(
        shape = ShapeDefaults.Large,
        tonalElevation = 3.dp,
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(bottom = 1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = additionPadding.coerceAtLeast(0.dp))


        ) {


            Image(
                painter = rememberAsyncImagePainter(monster.IMG_url),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(72.dp)
                    .padding(horizontal = 1.dp, vertical = 1.dp)
                    .clip(CircleShape)
            )
            Text(
                text = monster.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 30.dp, end = 10.dp)

            )


            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            Checkbox(
                checked = checked,
                onCheckedChange = onClickChecked,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .scale(0.7f)
            )

            IconButton(
                onClick = onClickExpanded,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                )
            }

        }
        if (expanded) {
            Row(
                modifier = Modifier.padding(
                    start = 82.dp,
                    top = 60.dp
                )
            ) {
                Text(
                    text = "attack : ".plus(monster.attack).plus("\n").plus("defence : ")
                        .plus(monster.defence).plus("\n").plus("damage : ").plus(monster.damageFrom)
                         .plus(" - ").plus(monster.damageTo).plus("\n").plus("health : ")
                        .plus(monster.health).plus("\n").plus("growth : ").plus(monster.growth)
                        .plus("\n").plus("cost : ").plus(monster.cost).plus("\n").plus("speed : ")
                        .plus(monster.speed).plus("\n"),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row() {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = onClickCoLevel,
                        shape = ShapeDefaults.Medium,
                        modifier = Modifier
                            .padding(top = 90.dp, end = 10.dp)
                    ) {
                        Text(text = "Co-leveled")
                    }
                }
            }
        }
    }
}