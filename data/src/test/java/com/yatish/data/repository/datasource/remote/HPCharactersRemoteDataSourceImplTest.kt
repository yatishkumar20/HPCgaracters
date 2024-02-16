package com.yatish.data.repository.datasource.remote

import com.yatish.data.TestData.characterListDto
import com.yatish.data.mapper.CharactersMapper
import com.yatish.data.remote.HPCharactersAPI
import com.yatish.domain.Result
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
class HPCharactersRemoteDataSourceImplTest {

    private val api: HPCharactersAPI = mockk()

    private lateinit var dataSource: HPCharactersRemoteDataSource
    private val mapper = CharactersMapper()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        dataSource = HPCharactersRemoteDataSourceImpl(
            api,
            mapper
        )
    }

    @Test
    fun `WHEN data source called THEN return list of characters`() = runTest {
        coEvery { api.getAllCharacters() } returns characterListDto

        val result = dataSource.getCharacters()

        Assert.assertTrue(result is Result.Success)
    }

    @Test
    fun `WHEN data source called THEN through exception`() = runTest {
        coEvery { api.getAllCharacters() } throws Exception(EXCEPTION_MESSAGE)
        val result = dataSource.getCharacters()

        Assert.assertTrue(result is Result.Error)
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN return character details`() = runTest {
        coEvery { api.getCharacter(CHARACTER_ID) } returns characterListDto

        val result = dataSource.getCharacter(CHARACTER_ID)
        Assert.assertTrue(result is Result.Success)
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN through exception`() = runTest {
        coEvery { api.getCharacter(CHARACTER_ID) } throws Exception(EXCEPTION_MESSAGE)
        val result = dataSource.getCharacter(CHARACTER_ID)

        Assert.assertTrue(result is Result.Error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object {
        const val CHARACTER_ID = "1"
        const val EXCEPTION_MESSAGE = "Unknown error"
    }
}