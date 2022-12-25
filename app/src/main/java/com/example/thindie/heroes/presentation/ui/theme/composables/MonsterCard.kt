package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.heroes.R
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme

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
            .widthIn(200.dp, 300.dp)
            .height(52.dp)
    ) {

        Image(
            painter =  rememberAsyncImagePainter(imageUrl),
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
            modifier = Modifier.padding(start = 8.dp, end = 10.dp)

        )

        Checkbox(
            checked = false,
            onCheckedChange = onCheckedChange,
            modifier = modifier

                .weight(1f)
                .padding(end = 8.dp)

        )
    }

}


@Preview(showSystemUi = false, showBackground = true)
@Composable
fun MonsterCardPreview() {
    HeroesTheme() {
        MonsterCard(monsterName = "BLACK DRAGON", imageUrl = "", onCheckedChange = {})
    }
}