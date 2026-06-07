package com.moodreel.data.tmdb.mapper

import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import com.moodreel.core.model.Movie
import com.moodreel.data.tmdb.dto.SearchResponseDto
import com.moodreel.data.tmdb.dto.SearchResultDto

internal fun SearchResponseDto.toDomain(): List<Movie> =
    results.mapNotNull { it.toMovieOrNull() }

internal fun SearchResultDto.toMovieOrNull(): Movie? {
    val type = parseMediaType(mediaType) ?: return null
    val resolvedTitle = title ?: name ?: return null
    val resolvedDate = releaseDate ?: firstAirDate

    return Movie(
        id = MediaId(id),
        title = resolvedTitle,
        mediaType = type,
        posterUrl = buildImageUrl(posterPath, TmdbImageSize.Medium),
        releaseYear = extractYear(resolvedDate),
        rating = voteAverage,
        overview = overview.orEmpty(),
    )
}

private fun parseMediaType(raw: String?): MediaType? = when (raw) {
    "movie" -> MediaType.MOVIE
    "tv" -> MediaType.TV_SERIES
    else -> null
}

private fun extractYear(date: String?): Int? {
    if (date.isNullOrBlank()) return null
    return date.take(YEAR_LENGTH).toIntOrNull()
}

private const val YEAR_LENGTH = 4
