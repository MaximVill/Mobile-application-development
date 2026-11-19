package com.example.mobileapplicationdevelopment.data.network

import com.example.mobileapplicationdevelopment.data.model.CharacterDto
import com.example.mobileapplicationdevelopment.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("api/character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse

    @GET("api/character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto
}
