package com.example.mobileapplicationdevelopment.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mobileapplicationdevelopment.data.model.CharacterDto
import com.example.mobileapplicationdevelopment.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    repository: CharacterRepository
) : ViewModel() {

    val charactersFlow: Flow<PagingData<CharacterDto>> = repository.getCharacters()
        .cachedIn(viewModelScope)
}
