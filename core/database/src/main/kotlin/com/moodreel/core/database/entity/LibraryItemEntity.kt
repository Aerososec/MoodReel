package com.moodreel.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaType

@Entity(
    tableName = "library_items",
    primaryKeys = ["media_id", "media_type"],
    indices = [Index(value = ["status", "added_at"])]
)
data class LibraryItemEntity(
    @ColumnInfo("media_id") val mediaId: Int,
    @ColumnInfo("media_type") val mediaType: MediaType,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("poster_url") val posterUrl: String?,
    @ColumnInfo("release_year") val releaseYear: Int?,
    @ColumnInfo("status") val status: LibraryStatus,
    @ColumnInfo("added_at") val addedAt: Long
)
