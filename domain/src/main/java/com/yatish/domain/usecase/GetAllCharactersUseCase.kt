package com.yatish.domain.usecase

import com.yatish.domain.Result
import com.yatish.domain.model.CharacterModel

interface GetAllCharactersUseCase {
    suspend operator fun invoke(): Result<List<CharacterModel>>
}