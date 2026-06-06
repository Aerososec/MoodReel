package com.moodreel.app.di

import com.moodreel.app.BuildConfig
import com.moodreel.data.tmdb.TmdbApiTokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object TmdbModule {
    @Provides
    @Singleton
    fun providesTmdbApiTokenProvider(): TmdbApiTokenProvider = object : TmdbApiTokenProvider {
        override fun getToken(): String = BuildConfig.TMDB_API_TOKEN
    }
}
