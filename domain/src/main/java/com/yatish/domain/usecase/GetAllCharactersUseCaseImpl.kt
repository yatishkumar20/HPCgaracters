package com.yatish.domain.usecase

import com.yatish.domain.model.CharacterModel
import com.yatish.domain.repository.HPCharactersRepository
import javax.inject.Inject

class GetAllCharactersUseCaseImpl @Inject constructor(
    private val repository: HPCharactersRepository
): GetAllCharactersUseCase {
    override suspend fun invoke(): Result<List<CharacterModel>> = repository.getCharacters()
}