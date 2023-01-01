package com.example.thindie.heroes.presentation.ui.theme.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.thindie.heroes.presentation.HeroesViewModel
import com.example.thindie.heroes.presentation.ui.theme.shapes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HeroesSearchBar(viewModel: HeroesViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val enteredTextOnSearchBar = rememberSaveable { mutableStateOf("") }
    val clickedOnSearch = rememberSaveable { mutableStateOf(false) }

    val allMonsters = viewModel.representAllMonsterList.observeAsState()


    Surface(
        color = MaterialTheme.colorScheme.onSecondary,
        shape = shapes.extraLarge,
        modifier = Modifier
            .padding(start = 20.dp, top = 54.dp, end = 20.dp, bottom = 20.dp)
            .scale(0.8f)
            .height(50.dp)
            .requiredWidthIn(min = 400.dp)

    )
    {
        TextField(
            value = enteredTextOnSearchBar.value,
            onValueChange = { string ->
                enteredTextOnSearchBar.value = string
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable
                            (
                            enabled = true,
                            onClick = {
                                viewModel.representUserBehavior(
                                    enteredTextOnSearchBar.value,
                                    null,
                                    allMonsters.value
                                )
                                keyboardController!!.hide()
                                enteredTextOnSearchBar.value = ""
                                clickedOnSearch.value = !clickedOnSearch.value
                            }
                        )
                        .scale(1.3f)
                        .clip(ShapeDefaults.Large)
                )
            },
            colors = TextFieldDefaults.textFieldColors(MaterialTheme.colorScheme.onPrimaryContainer),
            placeholder = {
                Text(
                    text = "Search from all creatures..",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            textStyle = MaterialTheme.typography.titleLarge
        )
    }
}
