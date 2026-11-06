package com.example.mobileapplicationdevelopment.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mobileapplicationdevelopment.R

/**
 * Класс состояния UI для приложения Art Space.
 * Содержит текущий шаг и идентификаторы ресурсов для произведения искусства.
 */
data class ArtSpaceUiState(
    val currentStep: Int = 1,
    @param:DrawableRes val imageRes: Int = R.drawable.ic_art_1,
    @param:StringRes val titleRes: Int = R.string.title_1,
    @param:StringRes val artistRes: Int = R.string.artist_1,
    @param:StringRes val yearRes: Int = R.string.year_1,
)
