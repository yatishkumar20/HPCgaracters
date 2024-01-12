package com.yatish.data.repository

import com.yatish.data.TestData
import com.yatish.data.repository.datasource.remote.HPCharactersRemoteDataSource
import com.yatish.domain.repository.HPCharactersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HPCharactersRepositoryImplTest {

    private val dataSource: HPCharactersRemoteDataSource = mockk()
    private lateinit var repository: HPCharactersRepository

    @Before
    fun setUp() {
        repository = HPCharactersRepositoryImpl(dataSource)
    }

    @Test
    fun `WHEN repository called THEN return the list of Characters`() = runTest {
        coEvery { repository.getCharacters() } returns Result.success(TestData.charactersList)

        repository.getCharacters().onSuccess {
            Assert.assertEquals(CHARACTER_NAME_1, it[0].name)
        }
    }

    @Test
    fun `GIVEN characterId WHEN repository called THEN return the character details`() = runTest {
        coEvery { repository.getCharacter(CHARACTER_ID) } returns Result.success(TestData.characterModel)

        repository.getCharacter(CHARACTER_ID).onSuccess {
            Assert.assertEquals(CHARACTER_NAME_2, it.actor)
        }
    }

    private companion object {
        const val CHARACTER_ID = "1"
        const val CHARACTER_NAME_1 = "Harry Potter"
        const val CHARACTER_NAME_2 = "Daniel Radcliffe"
    }

}