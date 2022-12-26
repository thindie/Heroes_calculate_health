package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.heroes.R
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.FractionPair
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme
import com.example.thindie.heroes.presentation.ui.theme.shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FractionElement(
    fractionPair: FractionPair,
    viewModel: HeroesViewModel,
    modifier: Modifier = Modifier,
) {
    Surface(onClick = {viewModel.getFraction(fraction = fractionPair.fraction) }) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier

        ) {
            Image(
                painter = rememberAsyncImagePainter(fractionPair.IMG_url),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text =  fractionPair.fraction.name,
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

    }
}

