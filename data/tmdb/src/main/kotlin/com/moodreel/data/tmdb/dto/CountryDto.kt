package com.moodreel.data.tmdb.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CountryDto(
    @SerialName("iso_3166_1") val iso: String,
    val name: String,
)
