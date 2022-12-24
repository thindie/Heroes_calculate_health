package com.example.thindie.heroes.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.R
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.Monster
import com.example.thindie.heroes.domain.entities.MonsterLevel
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme


@Composable
fun Heroes(viewModel: HeroesViewModel) {

}

@Composable
fun FractionElement(
   //@DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier

    ) {
        Image(
            painter = painterResource(id=  R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Composable
fun MonsterCard(
    imageUrl: String,
    monsterName: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(36.dp)
                .padding(horizontal = 8.dp)
                .clip(CircleShape)
        )
        Text(
            text = monsterName,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Checkbox(
            checked = false,
            onCheckedChange = onCheckedChange,
            modifier = modifier
                .align(alignment = Alignment.CenterVertically)
                .weight(1f)
                .padding(end = 8.dp)

        )
    }

}

@Composable
fun MonsterColumn(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,

        ) {
        items(monsterList()) { monster ->
            MonsterCard(imageUrl = "", monsterName = monster.name, onCheckedChange = {})
        }
    }
}


@Composable
fun FractionRow(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,

        ) {
        items(fractionList()) { fraction ->
            FractionElement(

                text =   R.string.app_name,
                modifier = modifier.padding(end = 8.dp)
            )
        }
    }
}


@Preview(showSystemUi = false, showBackground = true)
@Composable
fun MonsterCardPreview() {
    HeroesTheme() {
        MonsterCard(monsterName = "BLACK DRAGON", imageUrl = "", onCheckedChange = {})
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun FractionRowPreview() {
    HeroesTheme {
        FractionRow()
    }
}


@Preview(showBackground = true)
@Composable
fun HeroesPreview() {
    HeroesTheme {
        Column {
            FractionRow()
            MonsterColumn()
        }

    }
}

fun monsterList(): List<Monster> {
    val list: MutableList<Monster> = mutableListOf()
    for (i in 1..10) {
        list.add(
            Monster(
                "monster $i", i, i, i, i, i, i, i, i, Fraction.INFERNO, MonsterLevel.SIX, ""

            )
        )
    }
    return list
}

fun fractionList(): List<Fraction> {
    val list: MutableList<Fraction> = mutableListOf(
        Fraction.INFERNO,
        Fraction.RAMPART,
        Fraction.FORTRESS,
        Fraction.CASTLE,
        Fraction.CITADEL,
        Fraction.CONFLUX,
        Fraction.TOWER
    )

    return list
}


@Preview(showBackground = true)
@Composable
fun FractionElementPreview() {
    HeroesTheme() {
        FractionElement(

            text = R.string.app_name
        )
    }
}