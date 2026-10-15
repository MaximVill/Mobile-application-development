package com.example.mobileapplicationdevelopment.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameRes: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)
