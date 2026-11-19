package com.example.mobileapplicationdevelopment.di

import com.example.mobileapplicationdevelopment.data.network.RickAndMortyApiService
import com.example.mobileapplicationdevelopment.data.repository.CharacterRepository
import com.example.mobileapplicationdevelopment.data.repository.CharacterRepositoryImpl
import com.example.mobileapplicationdevelopment.ui.characters.CharactersViewModel
import com.example.mobileapplicationdevelopment.ui.characterdetail.CharacterDetailViewModel
import kotlinx.serialization.json.Json
import okhttp.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val appModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RickAndMortyApiService::class.java)
    }

    single<CharacterRepository> {
        CharacterRepositoryImpl(get())
    }

    viewModel {
        CharactersViewModel(get())
    }

    viewModel { (characterId: Int) ->
        CharacterDetailViewModel(characterId, get())
    }
}
