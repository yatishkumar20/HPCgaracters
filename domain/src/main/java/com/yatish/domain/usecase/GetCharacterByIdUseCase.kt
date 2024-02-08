package com.yatish.domain.usecase

import com.yatish.domain.Result
import com.yatish.domain.model.CharacterModel

interface GetCharacterByIdUseCase {
    suspend operator fun invoke(id: String): Result<CharacterModel>
}