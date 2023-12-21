package com.yatish.presentation.ui.character_list

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.turbine.test
import com.yatish.domain.usecase.GetAllCharactersUseCase
import com.yatish.presentation.TestData
import com.yatish.presentation.mapper.CharacterItemMapper
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
class CharacterListViewModelTest {
    private val getAllCharactersUseCase: GetAllCharactersUseCase = mockk()
    private val mapper: CharacterItemMapper = mockk()

    private lateinit var viewModel: CharacterListViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CharacterListViewModel(
            getAllCharactersUseCase,
            mapper
        )
    }

    @Test
    fun `GIVEN usecase WHEN called to get all characters THEN return success state`() = runTest {
        coEvery { getAllCharactersUseCase() } returns Result.success(TestData.charactersList)
        coEvery { mapper.map(TestData.charactersList[0]) } returns TestData.characterUIModel

        viewModel.sendIntent(CharacterListContract.ViewIntent.LoadData)
        Assert.assertTrue(viewModel.viewState.value is CharacterListContract.ViewState.Success)
    }

    @Test
    fun `GIVEN usecase WHEN called to get all characters THEN return error state`() = runTest {
        coEvery { getAllCharactersUseCase() } returns Result.failure(Exception())

        viewModel.sendIntent(CharacterListContract.ViewIntent.LoadData)
        Assert.assertTrue(viewModel.viewState.value is CharacterListContract.ViewState.Error)
    }

    @Test
    fun `WHEN item clicked THEN emit navigation side effect`() = runTest {
        viewModel.sideEffect.test {
            viewModel.sendIntent(CharacterListContract.ViewIntent.OnCharacterItemClick("1", "Harry Potter"))
            Assert.assertTrue(awaitItem() is CharacterListContract.SideEffect.NavigateToDetails)
        }
    }
}