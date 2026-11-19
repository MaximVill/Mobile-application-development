package com.example.mobileapplicationdevelopment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mobileapplicationdevelopment.data.model.CharacterDto
import com.example.mobileapplicationdevelopment.data.network.RickAndMortyApiService
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<CharacterDto>>
    suspend fun getCharacterById(id: Int): CharacterDto
}

class CharacterRepositoryImpl(
    private val apiService: RickAndMortyApiService
) : CharacterRepository {
    override fun getCharacters(): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }

    override suspend fun getCharacterById(id: Int): CharacterDto {
        return apiService.getCharacterById(id)
    }
}
