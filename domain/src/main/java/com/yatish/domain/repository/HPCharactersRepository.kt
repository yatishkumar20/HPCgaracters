package com.yatish.domain.repository

import com.yatish.domain.Result
import com.yatish.domain.model.CharacterModel

interface HPCharactersRepository {

    suspend fun getCharacters(): Result<List<CharacterModel>>

    suspend fun getCharacter(id: String): Result<CharacterModel>
}