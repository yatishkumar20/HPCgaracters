package com.yatish.data.repository.datasource.remote

import com.yatish.data.TestData.characterListDto
import com.yatish.data.mapper.CharactersMapper
import com.yatish.data.remote.HPCharactersAPI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class HPCharactersRemoteDataSourceImplTest {

    private val api: HPCharactersAPI = mockk()

    private lateinit var dataSource: HPCharactersRemoteDataSource
    private val dispatcher = Dispatchers.IO
    private val mapper = CharactersMapper()

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
            Assert.assertEquals("Harry Potter", it[0].name)
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
        coEvery { api.getAllCharacters() } throws Exception("Unknown error")
        val result = dataSource.getCharacters()

        assert(result.isFailure)
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN return character details`() = runTest {
        val characterId = "1"
        coEvery { api.getCharacter(characterId) } returns Response.success(characterListDto)

        dataSource.getCharacter(characterId).onSuccess {
            Assert.assertEquals("Harry Potter", it.name)
        }
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN return empty data`() = runTest {
        val characterId = "1"
        coEvery { api.getCharacter(characterId) } returns Response.success(null)

        val result = dataSource.getCharacter(characterId)

        assert(result.isFailure)
    }

    @Test
    fun `GIVEN characterId WHEN data source called THEN through exception`() = runTest {
        val characterId = "1"
        coEvery { api.getCharacter(characterId) } throws Exception("Unknown error")
        val result = dataSource.getCharacter(characterId)

        assert(result.isFailure)
    }
}