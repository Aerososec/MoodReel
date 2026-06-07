package com.moodreel.data.tmdb.mapper

import com.moodreel.core.model.ContentStatus
import com.moodreel.core.model.Genre
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import com.moodreel.core.model.MovieDetails
import com.moodreel.data.tmdb.dto.GenreDto
import com.moodreel.data.tmdb.dto.MovieDetailsDto
import com.moodreel.data.tmdb.dto.TvShowDetailsDto

internal fun MovieDetailsDto.toDomain(): MovieDetails = MovieDetails(
    id = MediaId(id),
    title = title,
    originalTitle = originalTitle.orEmpty(),
    mediaType = MediaType.MOVIE,
    posterUrl = buildImageUrl(posterPath, TmdbImageSize.Large),
    backdropUrl = buildImageUrl(backdropPath, TmdbImageSize.Original),
    releaseYear = extractYearOrNull(releaseDate),
    rating = voteAverage,
    voteCount = voteCount,
    overview = overview.orEmpty(),
    genres = genres.map { it.toDomain() },
    runtimeMinutes = runtime,
    productionCountries = productionCountries.map { it.name },
    status = parseContentStatus(status),
    tagline = tagline.takeIf { !it.isNullOrBlank() },
)

internal fun TvShowDetailsDto.toDomain(): MovieDetails = MovieDetails(
    id = MediaId(id),
    title = name,
    originalTitle = originalName.orEmpty(),
    mediaType = MediaType.TV_SERIES,
    posterUrl = buildImageUrl(posterPath, TmdbImageSize.Large),
    backdropUrl = buildImageUrl(backdropPath, TmdbImageSize.Original),
    releaseYear = extractYearOrNull(firstAirDate),
    rating = voteAverage,
    voteCount = voteCount,
    overview = overview.orEmpty(),
    genres = genres.map { it.toDomain() },
    runtimeMinutes = episodeRunTime.firstOrNull(),
    productionCountries = originCountry,
    status = parseContentStatus(status),
    tagline = tagline.takeIf { !it.isNullOrBlank() },
)

private fun GenreDto.toDomain(): Genre = Genre(id = id, name = name)

private fun parseContentStatus(raw: String?): ContentStatus = when (raw) {
    "Released", "Ended", "Returning Series" -> ContentStatus.RELEASED
    "In Production", "Post Production", "Pilot" -> ContentStatus.IN_PRODUCTION
    "Planned", "Rumored" -> ContentStatus.UPCOMING
    "Cancelled" -> ContentStatus.CANCELLED
    else -> ContentStatus.UNKNOWN
}

private fun extractYearOrNull(date: String?): Int? {
    if (date.isNullOrBlank()) return null
    return date.take(YEAR_LENGTH).toIntOrNull()
}

private const val YEAR_LENGTH = 4
