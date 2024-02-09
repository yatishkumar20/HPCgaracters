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

class GetAllCharactersUseCaseImplTest {

    private val repository: HPCharactersRepository = mockk()
    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    @Before
    fun setUp() {
        getAllCharactersUseCase = GetAllCharactersUseCaseImpl(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN usecase invoked THEN verify repository called`() = runTest {
        coEvery { repository.getCharacters() } returns Result.Success(emptyList())

        getAllCharactersUseCase.invoke()

        coVerify {
            repository.getCharacters()
        }

    }
}