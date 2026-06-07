package com.moodreel.data.tmdb.mapper

import com.moodreel.data.tmdb.BuildConfig

internal enum class TmdbImageSize(val pathSegment: String) {
    Small("w185"),
    Medium("w500"),
    Large("w780"),
    Original("original"),
}

internal fun buildImageUrl(path: String?, size: TmdbImageSize): String? {
    if (path.isNullOrBlank()) return null
    return "${BuildConfig.TMDB_IMAGE_BASE_URL}${size.pathSegment}$path"
}
