package com.moodreel.core.model

data class LibraryItem(
    val mediaId: MediaId,
    val mediaType: MediaType,
    val title: String,
    val posterUrl: String?,
    val releaseYear: Int?,
    val status: LibraryStatus,
    val addedAt: Long
)
