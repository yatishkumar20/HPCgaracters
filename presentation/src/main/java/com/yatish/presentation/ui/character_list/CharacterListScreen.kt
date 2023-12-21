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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yatish.presentation.model.CharacterItemUIModel
import com.yatish.presentation.ui.common.CircularProgressView
import com.yatish.presentation.ui.common.CustomText
import com.yatish.presentation.ui.common.ErrorView
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CharacterListScreen(
    onCharacterItemClick: (id: String, name: String) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.sendIntent(CharacterListContract.ViewIntent.LoadData)

        viewModel.sideEffect.collectLatest {
            if (it is CharacterListContract.SideEffect.NavigateToDetails) {
                onCharacterItemClick(it.id, it.name)
            }
        }
    }

    val result = viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (result.value) {
            is CharacterListContract.ViewState.Loading -> {
                CircularProgressView(modifier = Modifier.align(Alignment.Center))
            }
            is CharacterListContract.ViewState.Success -> {
                val data = (result.value as CharacterListContract.ViewState.Success).data
                CharacterListView(data, onItemClick = { id, name ->
                    viewModel.sendIntent(CharacterListContract.ViewIntent.OnCharacterItemClick(id, name))
                })
            }
            is CharacterListContract.ViewState.Error -> {
                val errorMessage = (result.value as CharacterListContract.ViewState.Error).throwable.message
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
    onItemClick: (id: String, name: String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data) { item ->
            CharacterItemView(item) { id, name ->
                onItemClick(id, name)
            }
        }
    }
}

@Composable
fun CharacterItemView(
    item: CharacterItemUIModel,
    onItemClick: (id: String, name: String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(item.id, item.name)
        }
        .padding(10.dp)
    ) {
        CustomText(text = item.name, style = MaterialTheme.typography.h6)
        CustomText(text = item.house, style = MaterialTheme.typography.body1)
    }
}

