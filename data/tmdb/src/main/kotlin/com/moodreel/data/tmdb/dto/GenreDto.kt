package com.moodreel.data.tmdb.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class GenreDto(
    val id: Int,
    val name: String,
)
