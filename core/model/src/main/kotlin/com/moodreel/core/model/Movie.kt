package com.moodreel.core.model

data class Movie(
    val id: MovieId,
    val title: String,
    val mediaType: MediaType,
    val posterUrl: String?,
    val releaseYear: Int?,
    val rating: Double?,
    val overview: String,
)
