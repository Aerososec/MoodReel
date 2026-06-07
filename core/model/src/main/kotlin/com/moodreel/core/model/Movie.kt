package com.moodreel.core.model

data class Movie(
    val id: MediaId,
    val title: String,
    val mediaType: MediaType,
    val posterUrl: String?,
    val releaseYear: Int?,
    val rating: Double?,
    val overview: String,
)
