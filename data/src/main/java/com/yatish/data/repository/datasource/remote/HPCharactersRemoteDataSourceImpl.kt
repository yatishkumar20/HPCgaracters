package com.yatish.data.repository.datasource.remote

import com.yatish.data.mapper.CharactersMapper
import com.yatish.data.remote.HPCharactersAPI
import com.yatish.data.repository.safeApiCall
import com.yatish.domain.Result
import com.yatish.domain.model.CharacterModel
import javax.inject.Inject

class HPCharactersRemoteDataSourceImpl @Inject constructor(
    private val api: HPCharactersAPI,
    private val mapper: CharactersMapper
) : HPCharactersRemoteDataSource {

    override suspend fun getCharacters(): Result<List<CharacterModel>> {
        return safeApiCall(
            {
                api.getAllCharacters()
            },
            { dto ->
                mapper.map(dto)
            }
        )
    }

    override suspend fun getCharacter(id: String): Result<CharacterModel> {
        return safeApiCall(
            {
                api.getCharacter(id)
            },
            { dto ->
                mapper.map(dto)[0]
            }
        )
    }
}