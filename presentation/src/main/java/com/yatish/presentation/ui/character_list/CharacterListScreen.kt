package com.yatish.presentation.ui.character_list

import CustomImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yatish.presentation.R
import com.yatish.presentation.model.CharacterItemUIModel
import com.yatish.presentation.theme.Black
import com.yatish.presentation.theme.White
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
        viewModel.sideEffect.collectLatest {
            if (it is CharacterListContract.ListScreenSideEffect.NavigateToDetails) {
                onCharacterItemClick(it.id, it.name)
            }
        }
    }

    val result = viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (result.value) {
            is CharacterListContract.ListScreenViewState.Loading -> {
                CircularProgressView(modifier = Modifier.align(Alignment.Center))
            }
            is CharacterListContract.ListScreenViewState.Success -> {
                val data = (result.value as CharacterListContract.ListScreenViewState.Success).data
                CharacterListView(data, onItemClick = { id, name ->
                    viewModel.sendIntent(
                        CharacterListContract.ListScreenViewIntent.OnCharacterItemClick(
                            id,
                            name
                        )
                    )
                })
            }
            is CharacterListContract.ListScreenViewState.Error -> {
                val errorMessage =
                    (result.value as CharacterListContract.ListScreenViewState.Error).throwable.message
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
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(item.id, item.name)
        }
        .padding(dimensionResource(id = R.dimen.padding_list))
    ) {
        CustomImageView(
            imageUrl = item.image, description = item.name, modifier = Modifier
                .clip(CircleShape)
                .background(if (item.image != "") Black else White)
                .size(dimensionResource(id = R.dimen.list_image_size))
        )

        Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.list_start_padding))) {
            CustomText(text = item.name, style = MaterialTheme.typography.h6)
            CustomText(text = item.house, style = MaterialTheme.typography.body1)
        }
    }
}

