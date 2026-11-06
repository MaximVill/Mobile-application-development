package com.example.mobileapplicationdevelopment.ui

import androidx.lifecycle.ViewModel
import com.example.mobileapplicationdevelopment.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel для управления состоянием и бизнес-логикой приложения Art Space.
 * Реализует паттерн UDF (Unidirectional Data Flow) с использованием StateFlow.
 */
class ArtSpaceViewModel : ViewModel() {

    // Приватная изменяемая переменная MutableStateFlow
    private val _uiState = MutableStateFlow(ArtSpaceUiState())

    // Публичная неизменяемая переменная StateFlow, получаемая с помощью asStateFlow()
    val uiState: StateFlow<ArtSpaceUiState> = _uiState.asStateFlow()

    /**
     * Переход к следующему произведению искусства.
     */
    fun nextArtwork() {
        val nextStep = if (_uiState.value.currentStep < 3) _uiState.value.currentStep + 1 else 1
        updateStateForStep(nextStep)
    }

    /**
     * Переход к предыдущему произведению искусства.
     */
    fun previousArtwork() {
        val prevStep = if (_uiState.value.currentStep > 1) _uiState.value.currentStep - 1 else 3
        updateStateForStep(prevStep)
    }

    /**
     * Обновление состояния на основе выбранного шага.
     */
    private fun updateStateForStep(step: Int) {
        val imageRes = when (step) {
            1 -> R.drawable.ic_art_1
            2 -> R.drawable.ic_art_2
            else -> R.drawable.ic_art_3
        }

        val titleRes = when (step) {
            1 -> R.string.title_1
            2 -> R.string.title_2
            else -> R.string.title_3
        }

        val artistRes = when (step) {
            1 -> R.string.artist_1
            2 -> R.string.artist_2
            else -> R.string.artist_3
        }

        val yearRes = when (step) {
            1 -> R.string.year_1
            2 -> R.string.year_2
            else -> R.string.year_3
        }

        _uiState.update { currentState ->
            currentState.copy(
                currentStep = step,
                imageRes = imageRes,
                titleRes = titleRes,
                artistRes = artistRes,
                yearRes = yearRes
            )
        }
    }
}
