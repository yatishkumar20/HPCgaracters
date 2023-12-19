package com.yatish.presentation.ui.character_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yatish.presentation.model.CharacterItemUIModel
import com.yatish.presentation.ui.common.CircularProgressView
import com.yatish.presentation.ui.common.CustomText

@Composable
fun CharacterListScreen(
    onCharacterItemClick: (id: String, name: String) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.sendIntent(CharacterListViewIntent.LoadData)
    }

    val result = viewModel.state.collectAsState(initial = CharacterListViewState.Loading)

    Box(modifier = Modifier.fillMaxSize()) {
        when (result.value) {
            is CharacterListViewState.Loading -> {
                CircularProgressView(modifier = Modifier.align(Alignment.Center))
            }
            is CharacterListViewState.Success -> {
                val data = (result.value as CharacterListViewState.Success).data
                CharacterListView(data, onCharacterItemClick)
            }
            is CharacterListViewState.Error -> {
                val errorMessage = (result.value as CharacterListViewState.Error).throwable.message
                errorMessage?.let {
                    ErrorView(it)
                }
            }
        }
    }
}

@Composable
fun CharacterListView(
    data: List<CharacterItemUIModel>,
    onCharacterItemClick: (id: String, name: String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data) { item ->
            CharacterItemView(item) { id, name ->
                onCharacterItemClick(id, name)
            }
        }
    }
}

@Composable
fun CharacterItemView(
    item: CharacterItemUIModel,
    onCharacterItemClick: (id: String, name: String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onCharacterItemClick(item.id, item.name)
        }
        .padding(10.dp)
    ) {
        CustomText(text = item.name, style = MaterialTheme.typography.h6)
        CustomText(text = item.house, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun ErrorView(message: String) {
    CustomText(
        text = message,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.error,
        modifier = Modifier.fillMaxHeight().padding(4.dp),
        textAlign = TextAlign.Center
    )
}

