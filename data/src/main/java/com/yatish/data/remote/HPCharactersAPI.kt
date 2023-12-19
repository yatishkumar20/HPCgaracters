package com.yatish.data.remote

import com.yatish.data.remote.dto.CharacterDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HPCharactersAPI {

    @GET("/api/characters")
    suspend fun getAllCharacters(): Response<List<CharacterDTO>>

    @GET("/api/character/{id}")
    suspend fun getCharacter(@Path("id") id: String): Response<CharacterDTO>
}