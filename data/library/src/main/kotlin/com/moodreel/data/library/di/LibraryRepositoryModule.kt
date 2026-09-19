package com.moodreel.data.library.di

import com.moodreel.core.domain.repository.LibraryRepository
import com.moodreel.data.library.repository.LibraryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LibraryRepositoryModule {
    @Binds
    fun bindLibraryRepository(impl: LibraryRepositoryImpl): LibraryRepository
}
