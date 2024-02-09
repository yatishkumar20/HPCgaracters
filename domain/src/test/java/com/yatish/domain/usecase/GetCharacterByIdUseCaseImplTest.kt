package com.yatish.domain.usecase

import com.yatish.domain.repository.HPCharactersRepository
import com.yatish.domain.Result
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
        coEvery { repository.getCharacter(CHARACTER_ID) } returns Result.Error(Exception())

        getCharacterByIdUseCase.invoke(CHARACTER_ID)

        coVerify {
            repository.getCharacter(CHARACTER_ID)
        }
    }

    private companion object {
        const val CHARACTER_ID = "1"
    }

}