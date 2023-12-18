package com.yatish.domain.usecase

import com.yatish.domain.model.CharacterModel
import com.yatish.domain.repository.HPCharactersRepository
import javax.inject.Inject

class GetCharacterByIdUseCaseImpl @Inject constructor(
    private val repository: HPCharactersRepository
): GetCharacterByIdUseCase {
    override suspend fun invoke(id: String): Result<CharacterModel> = repository.getCharacter(id)
}