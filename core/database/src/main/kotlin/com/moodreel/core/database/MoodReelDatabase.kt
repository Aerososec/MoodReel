package com.moodreel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moodreel.core.database.converters.Converters
import com.moodreel.core.database.dao.LibraryDao
import com.moodreel.core.database.entity.LibraryItemEntity

@Database(
    entities = [LibraryItemEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
internal abstract class MoodReelDatabase : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao
}
