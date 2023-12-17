package com.yatish.data.repository.datasource.remote

import com.yatish.domain.model.CharacterModel

interface HPCharactersRemoteDataSource {

    suspend fun getCharacters(): Result<List<CharacterModel>>

    suspend fun getCharacter(id: String): Result<CharacterModel>

}