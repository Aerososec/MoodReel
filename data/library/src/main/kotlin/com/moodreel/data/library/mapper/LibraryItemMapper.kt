package com.moodreel.data.library.mapper

import com.moodreel.core.database.entity.LibraryItemEntity
import com.moodreel.core.model.LibraryItem
import com.moodreel.core.model.MediaId

internal fun LibraryItem.toEntity(): LibraryItemEntity =
    LibraryItemEntity(
        mediaId = mediaId.value,
        mediaType = mediaType,
        title = title,
        posterUrl = posterUrl,
        releaseYear = releaseYear,
        status = status,
        addedAt = addedAt
    )

internal fun LibraryItemEntity.toDomain(): LibraryItem =
    LibraryItem(
        mediaId = MediaId(mediaId),
        mediaType = mediaType,
        title = title,
        posterUrl = posterUrl,
        releaseYear = releaseYear,
        status = status,
        addedAt = addedAt
    )
