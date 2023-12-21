package com.yatish.presentation.ui.character_details

import com.yatish.domain.usecase.GetCharacterByIdUseCase
import com.yatish.presentation.TestData
import com.yatish.presentation.mapper.CharacterDetailMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {

    private val getCharacterByIdUseCase: GetCharacterByIdUseCase = mockk()
    private val mapper: CharacterDetailMapper = mockk()

    private lateinit var viewModel: CharacterDetailViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CharacterDetailViewModel(
            getCharacterByIdUseCase,
            mapper
        )
    }

    @Test
    fun `GIVEN characterId WHEN called usecase THEN return success state`() = runTest {
        val characterId = "1"
        coEvery { getCharacterByIdUseCase(characterId) } returns Result.success(TestData.characterModel)
        coEvery { mapper.map(TestData.characterModel) } returns TestData.characterDetailsUIModel

        viewModel.sendIntent(CharacterDetailsContract.ViewIntent.LoadData(characterId))
        Assert.assertTrue(viewModel.viewState.value is CharacterDetailsContract.ViewState.Success)
    }

    @Test
    fun `GIVEN usecase WHEN called to get all characters THEN return error state`() = runTest {
        val characterId = "1"
        coEvery { getCharacterByIdUseCase(characterId) } returns Result.failure(Exception())

        viewModel.sendIntent(CharacterDetailsContract.ViewIntent.LoadData(characterId))
        Assert.assertTrue(viewModel.viewState.value is CharacterDetailsContract.ViewState.Error)
    }

}