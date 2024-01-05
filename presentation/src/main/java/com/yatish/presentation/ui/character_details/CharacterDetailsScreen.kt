package com.yatish.presentation.ui.character_details

import CustomImageView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val (image, name, dobLabel, dob, houseLabel, house, studentLabel, student, staffLabel, staff) = createRefs()

        CustomImageView(
            imageUrl = data.image,
            description = data.name,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(dimensionResource(id = R.dimen.image_padding))
                .height(dimensionResource(id = R.dimen.image_height))
                .width(dimensionResource(id = R.dimen.image_width))
        )

        CustomText(
            text = data.actor,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(image.bottom)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                }
                .padding(bottom = dimensionResource(id = R.dimen.padding_bottom))
        )

        val createGuidelineFromBottom = createGuidelineFromEnd(0.5f)

        CustomText(
            text = stringResource(id = R.string.date_of_birth),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(dobLabel) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(createGuidelineFromBottom)
                }
                .padding(start = dimensionResource(id = R.dimen.padding_start))
        )

        CustomText(
            text = data.dateOfBirth,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(dob) {
                    top.linkTo(dobLabel.bottom)
                    start.linkTo(dobLabel.start)
                    end.linkTo(dobLabel.end)
                }
                .padding(start = dimensionResource(id = R.dimen.padding_start))
        )

        CustomText(
            text = stringResource(id = R.string.house),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(houseLabel) {
                    top.linkTo(name.bottom)
                    start.linkTo(createGuidelineFromBottom)
                    end.linkTo(parent.end)
                }
                .padding(end = dimensionResource(id = R.dimen.padding_end))
        )

        CustomText(
            text = data.house,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(house) {
                    top.linkTo(houseLabel.bottom)
                    start.linkTo(houseLabel.start)
                    end.linkTo(houseLabel.end)
                }
                .padding(end = dimensionResource(id = R.dimen.padding_end))
        )

        CustomText(
            text = stringResource(id = R.string.hogwarts_student),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(studentLabel) {
                    top.linkTo(dob.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(createGuidelineFromBottom)
                }
                .padding(
                    start = dimensionResource(id = R.dimen.padding_start),
                    top = dimensionResource(id = R.dimen.padding_top)
                )
        )

        CustomText(
            text = if (data.hogwartsStudent) stringResource(id = R.string.yes) else stringResource(
                id = R.string.no
            ),
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(student) {
                    top.linkTo(studentLabel.bottom)
                    start.linkTo(studentLabel.start)
                    end.linkTo(studentLabel.end)
                }
                .padding(start = dimensionResource(id = R.dimen.padding_start))
        )

        CustomText(
            text = stringResource(id = R.string.hogwarts_staff),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(staffLabel) {
                    top.linkTo(house.bottom)
                    start.linkTo(createGuidelineFromBottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    end = dimensionResource(id = R.dimen.padding_end),
                    top = dimensionResource(id = R.dimen.padding_top)
                )
        )
        CustomText(
            text = if (data.hogwartsStaff) stringResource(id = R.string.yes) else stringResource(
                id = R.string.no
            ),
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(staff) {
                    top.linkTo(staffLabel.bottom)
                    start.linkTo(staffLabel.start)
                    end.linkTo(staffLabel.end)
                }
                .padding(end = dimensionResource(id = R.dimen.padding_end))
        )

    }
}
