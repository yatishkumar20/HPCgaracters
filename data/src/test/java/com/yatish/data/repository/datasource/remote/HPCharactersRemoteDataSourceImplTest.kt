package com.yatish.data.repository.datasource.remote

import com.yatish.data.TestData.characterListDto
import com.yatish.data.mapper.CharactersMapper
import com.yatish.data.remote.HPCharactersAPI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class HPCharactersRemoteDataSourceImplTest {

    private val api: HPCharactersAPI = mockk()

    private lateinit var dataSource: HPCharactersRemoteDataSource
    private val mapper = CharactersMapper()
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        dataSource = HPCharactersRemoteDataSourceImpl(
            dispatcher,
            api,
            mapper
        )
    }

    @Test
    fun `WHEN data source called THEN return list of characters`() = runTest {
        coEvery { api.getAllCharacters() } returns Response.success(characterListDto)

        dataSource.getCharacters().onSuccess {
            Assert.assertEquals(CHARACTER_NAME, it[0].name)
        }
    }

    @Test
    fun `WHEN data source called THEN return empty data`() = runTest {
        coEvery { api.getAllCharacters() } returns Response.success(null)

        val result = dataSource.getCharacters()

        assert(result.isFailure)
    }

    @Test
    fun `WHEN data source called THEN through exception`() = runTest {
        coEvery { api.getAllCharacters() } throws Exception(EXCEPTION_MESSAGE)
        val result = dataSource.getCharacters()

        assert(result.isFailure)
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN return character details`() = runTest {
        coEvery { api.getCharacter(CHARACTER_ID) } returns Response.success(characterListDto)

        dataSource.getCharacter(CHARACTER_ID).onSuccess {
            Assert.assertEquals(CHARACTER_NAME, it.name)
        }
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN return empty data`() = runTest {
        coEvery { api.getCharacter(CHARACTER_ID) } returns Response.success(null)

        val result = dataSource.getCharacter(CHARACTER_ID)

        assert(result.isFailure)
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN through exception`() = runTest {
        coEvery { api.getCharacter(CHARACTER_ID) } throws Exception(EXCEPTION_MESSAGE)
        val result = dataSource.getCharacter(CHARACTER_ID)

        assert(result.isFailure)
    }

    private companion object {
        const val CHARACTER_ID = "1"
        const val CHARACTER_NAME = "Harry Potter"
        const val EXCEPTION_MESSAGE = "Unknown error"
    }
}