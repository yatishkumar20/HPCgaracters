package com.yatish.domain.usecase

import com.yatish.domain.repository.HPCharactersRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetCharacterByIdUseCaseImplTest {

    private val repository: HPCharactersRepository = mockk()
    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @Before
    fun setUp() {
        getCharacterByIdUseCase = GetCharacterByIdUseCaseImpl(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GIVEN characterId WHEN usecase invoked THEN verify repository called`() = runTest {
        val characterId = "1"
        coEvery { repository.getCharacter(characterId) } returns Result.failure(Exception())

        getCharacterByIdUseCase.invoke(characterId)

        coVerify {
            repository.getCharacter(characterId)
        }
    }

}