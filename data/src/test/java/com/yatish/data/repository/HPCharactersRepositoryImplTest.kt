package com.yatish.data.repository

import com.yatish.data.TestData
import com.yatish.data.repository.datasource.remote.HPCharactersRemoteDataSource
import com.yatish.domain.repository.HPCharactersRepository
import com.yatish.domain.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HPCharactersRepositoryImplTest {

    private val dataSource: HPCharactersRemoteDataSource = mockk()
    private lateinit var repository: HPCharactersRepository
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = HPCharactersRepositoryImpl(dataSource)
    }

    @Test
    fun `WHEN repository called THEN return the list of Characters`() = runTest {
        coEvery { repository.getCharacters() } returns Result.Success(TestData.charactersList)

        val result = repository.getCharacters()

        Assert.assertTrue(result is Result.Success)
    }

    @Test
    fun `GIVEN characterId WHEN repository called THEN return the character details`() = runTest {
        coEvery { repository.getCharacter(CHARACTER_ID) } returns Result.Success(TestData.characterModel)

        val result = repository.getCharacter(CHARACTER_ID)

        Assert.assertTrue(result is Result.Success)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object {
        const val CHARACTER_ID = "1"
    }

}