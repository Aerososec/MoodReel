package com.moodreel.core.database.di

import android.content.Context
import androidx.room.Room
import com.moodreel.core.database.MoodReelDatabase
import com.moodreel.core.database.dao.LibraryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MoodReelDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MoodReelDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideLibraryDao(moodReelDatabase: MoodReelDatabase): LibraryDao {
        return moodReelDatabase.libraryDao()
    }

    private const val DATABASE_NAME = "MOOD_REEL_DATABASE"
}
