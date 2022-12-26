package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.presentation.fractionList
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme

@Composable
fun FractionRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(top = 8.dp, bottom = 30.dp),

        ) {
        items(fractionList()) { fraction ->
            FractionElement(
                fraction = fraction,
                modifier = modifier.padding(end = 8.dp),
                onClick = onClick,
                imageUrl = ""
            )
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun FractionRowPreview() {
    HeroesTheme {
        FractionRow(onClick = {})
    }
}