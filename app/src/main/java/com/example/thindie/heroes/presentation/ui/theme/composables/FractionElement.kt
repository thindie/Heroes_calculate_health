package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.heroes.R
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FractionElement(
    imageUrl: String,
    fraction: Fraction,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(onClick = onClick) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier

        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text =  fraction.name.toString(),
                modifier = Modifier,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun FractionElementPreview() {
    HeroesTheme() {
        FractionElement(
            fraction = Fraction.FORTRESS,
            imageUrl = "",
            onClick = {}
        )
    }
}

