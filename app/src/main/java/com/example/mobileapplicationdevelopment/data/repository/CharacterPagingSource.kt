package com.example.mobileapplicationdevelopment.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mobileapplicationdevelopment.data.model.CharacterDto
import com.example.mobileapplicationdevelopment.data.network.RickAndMortyApiService
import java.io.IOException

class CharacterPagingSource(
    private val apiService: RickAndMortyApiService
) : PagingSource<Int, CharacterDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        val pageNumber = params.key ?: 1
        return try {
            val response = apiService.getCharacters(pageNumber)
            val nextKey = if (response.info.next != null) pageNumber + 1 else null
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null

            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
