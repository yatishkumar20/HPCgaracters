package com.yatish.presentation.ui.character_details

import androidx.lifecycle.SavedStateHandle
import com.yatish.domain.usecase.GetCharacterByIdUseCase
import com.yatish.domain.Result
import com.yatish.presentation.TestData
import com.yatish.presentation.mapper.CharacterDetailMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {

    private val getCharacterByIdUseCase: GetCharacterByIdUseCase = mockk()
    private val mapper: CharacterDetailMapper = mockk()

    private lateinit var viewModel: CharacterDetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val savedStateHandle = mockk<SavedStateHandle>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { savedStateHandle.get<String>("id") } returns ID
        coEvery { getCharacterByIdUseCase(ID) } returns Result.Success(TestData.characterModel)
        coEvery { mapper.map(TestData.characterModel) } returns TestData.characterDetailsUIModel
        viewModel = CharacterDetailViewModel(
            getCharacterByIdUseCase,
            mapper,
            savedStateHandle
        )
    }

    @Test
    fun `GIVEN characterId WHEN called usecase THEN return success state`() = runTest {
        viewModel.sendIntent(CharacterDetailsContract.DetailScreenViewIntent.LoadData(ID))
        Assert.assertTrue(viewModel.viewState.value is CharacterDetailsContract.DetailScreenViewState.Success)
    }

    @Test
    fun `GIVEN usecase WHEN called to get all characters THEN return error state`() = runTest {
        coEvery { getCharacterByIdUseCase(ID) } returns Result.Error(Exception())
        viewModel.sendIntent(CharacterDetailsContract.DetailScreenViewIntent.LoadData(ID))

        advanceUntilIdle()
        Assert.assertTrue(viewModel.viewState.value is CharacterDetailsContract.DetailScreenViewState.Error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object {
        const val ID = "1"
    }

}