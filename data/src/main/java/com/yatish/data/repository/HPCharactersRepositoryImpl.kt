package com.yatish.data.repository

import com.yatish.data.repository.datasource.remote.HPCharactersRemoteDataSource
import com.yatish.domain.model.CharacterModel
import com.yatish.domain.repository.HPCharactersRepository
import javax.inject.Inject

class HPCharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: HPCharactersRemoteDataSource
) : HPCharactersRepository {
    override suspend fun getCharacters(): Result<List<CharacterModel>> =
        remoteDataSource.getCharacters()

    override suspend fun getCharacter(id: String): Result<CharacterModel> =
        remoteDataSource.getCharacter(id)
}