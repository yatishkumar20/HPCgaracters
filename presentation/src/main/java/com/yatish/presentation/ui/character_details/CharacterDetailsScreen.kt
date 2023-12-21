package com.yatish.presentation.ui.character_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.yatish.presentation.R
import com.yatish.presentation.model.CharacterDetailsUIModel
import com.yatish.presentation.ui.common.CircularProgressView
import com.yatish.presentation.ui.common.CustomText
import com.yatish.presentation.ui.common.ErrorView

@Composable
fun CharacterDetailsScreen(
    characterId: String,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.sendIntent(CharacterDetailsContract.ViewIntent.LoadData(characterId))
    }

    val result = viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (result.value) {
            is CharacterDetailsContract.ViewState.Loading -> {
                CircularProgressView(modifier = Modifier.align(Alignment.Center))
            }
            is CharacterDetailsContract.ViewState.Success -> {
                val data = (result.value as CharacterDetailsContract.ViewState.Success).data
                CharacterDetailsView(data)
            }
            is CharacterDetailsContract.ViewState.Error -> {
                val errorMessage =
                    (result.value as CharacterDetailsContract.ViewState.Error).throwable.message
                errorMessage?.let {
                    ErrorView(message = it)
                }
            }
        }
    }
}

@Composable
fun CharacterDetailsView(data: CharacterDetailsUIModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = data.image),
            contentDescription = data.name,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomText(
            text = data.actor,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    text = stringResource(id = R.string.date_of_birth),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                CustomText(
                    text = data.dateOfBirth,
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    text = stringResource(id = R.string.house),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                CustomText(
                    text = data.house,
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    text = stringResource(id = R.string.hogwarts_student),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                CustomText(
                    text = if (data.hogwartsStudent) stringResource(id = R.string.yes) else stringResource(
                        id = R.string.no
                    ),
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    text = stringResource(id = R.string.hogwarts_staff),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                CustomText(
                    text = if (data.hogwartsStaff) stringResource(id = R.string.yes) else stringResource(
                        id = R.string.no
                    ),
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
