package com.moodreel.core.model

data class MovieDetails(
    val id: MediaId,
    val title: String,
    val originalTitle: String,
    val mediaType: MediaType,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseYear: Int?,
    val rating: Double?,
    val voteCount: Int,
    val overview: String,
    val genres: List<Genre>,
    val runtimeMinutes: Int?,
    val productionCountries: List<String>,
    val status: ContentStatus,
    val tagline: String?,
)
