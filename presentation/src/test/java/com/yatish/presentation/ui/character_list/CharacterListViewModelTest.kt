package com.yatish.presentation.ui.character_list

import app.cash.turbine.test
import com.yatish.domain.usecase.GetAllCharactersUseCase
import com.yatish.domain.Result
import com.yatish.presentation.TestData
import com.yatish.presentation.mapper.CharacterItemMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {
    private val getAllCharactersUseCase: GetAllCharactersUseCase = mockk()
    private val mapper: CharacterItemMapper = mockk()

    private lateinit var viewModel: CharacterListViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getAllCharactersUseCase() } returns Result.Success(TestData.charactersList)
        coEvery { mapper.map(listOf(TestData.charactersList[0])) } returns listOf(TestData.characterUIModel)
        viewModel = CharacterListViewModel(
            testDispatcher,
            getAllCharactersUseCase,
            mapper
        )
    }

    @Test
    fun `GIVEN usecase WHEN called to get all characters THEN return success state`() = runTest {
        viewModel.sendIntent(CharacterListContract.ListScreenViewIntent.LoadData)
        Assert.assertTrue(viewModel.viewState.value is CharacterListContract.ListScreenViewState.Success)
    }

    @Test
    fun `GIVEN usecase WHEN called to get all characters THEN return error state`() = runTest {
        coEvery { getAllCharactersUseCase() } returns Result.Error(Exception())

        viewModel.sendIntent(CharacterListContract.ListScreenViewIntent.LoadData)
        advanceUntilIdle()
        Assert.assertTrue(viewModel.viewState.value is CharacterListContract.ListScreenViewState.Error)
    }

    @Test
    fun `WHEN item clicked THEN emit navigation side effect`() = runTest {
        viewModel.sideEffect.test {
            viewModel.sendIntent(CharacterListContract.ListScreenViewIntent.OnCharacterItemClick(ID, NAME))
            Assert.assertTrue(awaitItem() is CharacterListContract.ListScreenSideEffect.NavigateToDetails)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object {
        const val ID = "1"
        const val NAME = "1"
    }
}