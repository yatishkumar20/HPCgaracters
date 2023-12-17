package com.yatish.data.repository.datasource.remote

import com.yatish.data.di.IoDispatcher
import com.yatish.data.mapper.CharactersMapper
import com.yatish.data.remote.HPCharactersAPI
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
                    return@withContext Result.success(characters)
                } ?: run {
                    return@withContext Result.failure(Exception(charactersResponse.message()))
                }
            } catch (exception: Exception) {
                return@withContext Result.failure(exception)
            }
        }

    override suspend fun getCharacter(id: String): Result<CharacterModel> =
        withContext(dispatcher) {
            try {
                val characterResponse = api.getCharacter(id)
                characterResponse.body()?.let { response ->
                    val character = mapper.map(response)
                    return@withContext Result.success(character)
                } ?: run {
                    return@withContext Result.failure(Exception(characterResponse.message()))
                }
            } catch (exception: Exception) {
                return@withContext Result.failure(exception)
            }
        }
}