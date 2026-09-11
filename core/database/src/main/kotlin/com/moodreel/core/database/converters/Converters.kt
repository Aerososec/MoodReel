package com.moodreel.core.database.converters

import androidx.room.TypeConverter
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaType

internal class Converters {
    @TypeConverter
    fun toMediaType(value: String) = MediaType.valueOf(value)

    @TypeConverter
    fun toLibraryStatus(value: String) = LibraryStatus.valueOf(value)

    @TypeConverter
    fun fromMediaType(mediaType: MediaType) = mediaType.name

    @TypeConverter
    fun fromLibraryStatus(libraryStatus: LibraryStatus) = libraryStatus.name
}
