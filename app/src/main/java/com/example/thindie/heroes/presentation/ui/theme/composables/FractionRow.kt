package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.heroes.domain.entities.Fraction
import com.example.thindie.heroes.domain.entities.FractionPair
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.ui.theme.HeroesTheme

@Composable
fun FractionRow(
    list: State<List<FractionPair>?>,
    viewModel: HeroesViewModel,
    modifier: Modifier = Modifier
) {
    Surface(color = MaterialTheme.colorScheme.onSecondary, modifier = modifier)   {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            contentPadding = PaddingValues(horizontal = 2.dp),
            modifier = modifier.padding(top = 3.dp, bottom = 3.dp),

            ) {
            items(list.value!!) { fraction ->
                FractionElement(
                    fractionPair = fraction,
                    viewModel = viewModel
                )
            }
        }
    }

}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun FractionRowPreview() {
    HeroesTheme {

    }
}