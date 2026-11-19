package com.example.mobileapplicationdevelopment.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CharacterResponse(
    val info: PageInfo,
    val results: List<CharacterDto>
)

@Serializable
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationInfo,
    val location: LocationInfo,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class LocationInfo(
    val name: String,
    val url: String
)
