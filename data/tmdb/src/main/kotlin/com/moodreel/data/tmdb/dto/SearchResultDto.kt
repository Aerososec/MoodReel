package com.moodreel.data.tmdb.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchResultDto(
    val id: Int,
    @SerialName("media_type") val mediaType: String? = null,

    // Для фильмов
    val title: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,

    // Для сериалов
    val name: String? = null,
    @SerialName("first_air_date") val firstAirDate: String? = null,

    // Общие поля
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("overview") val overview: String? = null,
)
