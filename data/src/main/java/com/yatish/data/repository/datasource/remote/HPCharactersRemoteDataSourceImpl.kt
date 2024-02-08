package com.yatish.data.repository.datasource.remote

import com.yatish.data.di.IoDispatcher
import com.yatish.data.mapper.CharactersMapper
import com.yatish.data.remote.HPCharactersAPI
import com.yatish.domain.Result
import com.yatish.domain.model.CharacterModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HPCharactersRemoteDataSourceImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: HPCharactersAPI,
    private val mapper: CharactersMapper
) : HPCharactersRemoteDataSource {

    override suspend fun getCharacters(): Result<List<CharacterModel>> =
        withContext(dispatcher) {
            try {
                val charactersResponse = api.getAllCharacters()
                charactersResponse.body()?.let { response ->
                    val characters = response.map { mapper.map(it) }
                    return@withContext Result.Success(characters)
                } ?: run {
                    return@withContext Result.Error(Exception(charactersResponse.message()))
                }
            } catch (exception: Exception) {
                return@withContext Result.Error(exception)
            }
        }

    override suspend fun getCharacter(id: String): Result<CharacterModel> =
        withContext(dispatcher) {
            try {
                val characterResponse = api.getCharacter(id)
                characterResponse.body()?.let { response ->
                    val character = mapper.map(response[0])
                    return@withContext Result.Success(character)
                } ?: run {
                    return@withContext Result.Error(Exception(characterResponse.message()))
                }
            } catch (exception: Exception) {
                return@withContext Result.Error(exception)
            }
        }
}