package com.moodreel.data.tmdb.di

import com.moodreel.core.domain.repository.MovieRepository
import com.moodreel.data.tmdb.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface MovieRepositoryModule {
    @Binds
    @Singleton
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}
