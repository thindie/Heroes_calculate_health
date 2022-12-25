package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.presentation.monsterList

@Composable
fun MonsterColumn(
    modifier: Modifier = Modifier,
    list: List<Monster>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,

        ) {
        items(monsterList()) { monster ->
            MonsterCard(
                imageUrl = monster.IMG_url!!,
                monsterName = monster.name,
                onCheckedChange = {})
        }
    }
}