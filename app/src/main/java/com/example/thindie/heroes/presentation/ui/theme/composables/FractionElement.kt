package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.FractionToImage
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FractionElement(
    fractionToImage: FractionToImage,
    viewModel: HeroesViewModel,
) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = 28.dp,
        shadowElevation = 42.dp,
        onClick = { viewModel.representFractionColumn(fraction = fractionToImage.fraction);
           },
        modifier = Modifier
            .scale(1f)
            .clip(CircleShape)
            .size(84.dp)
            .shadow(68.dp)
            .fillMaxWidth()

    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = rememberAsyncImagePainter(fractionToImage.IMG_url),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = fractionToImage.fraction.name,
                modifier = Modifier,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}


