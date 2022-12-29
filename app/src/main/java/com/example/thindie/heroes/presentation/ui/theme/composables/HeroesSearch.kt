package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.ui.theme.shapes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HeroesSearchBar(
    viewModel: HeroesViewModel,
    modifier: Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val line = rememberSaveable { mutableStateOf("") }
    Surface(
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier
            .padding(start = 20.dp, top = 54.dp, end = 20.dp, bottom = 20.dp)
            .scale(0.8f)
            .fillMaxWidth(),
        shape = shapes.extraLarge,
    ) {
        TextField(
            value = line.value,
            onValueChange = { string -> line.value = string },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable
                            (
                            enabled = true,
                            onClick = {
                                viewModel.searchEngine(line.value, null); line.value = "";
                                keyboardController!!.hide()
                            }
                        )
                        .scale(1.3f)
                        .clip(ShapeDefaults.Large)
                )
            },
            colors = TextFieldDefaults.textFieldColors(MaterialTheme.colorScheme.onPrimaryContainer),
            placeholder = {
                Text(text = "Search from all creatures..",
                    style = MaterialTheme.typography.titleLarge
                    )
            },
            textStyle = MaterialTheme.typography.titleLarge
            )


    }

}
