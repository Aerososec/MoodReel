package com.moodreel.data.library.repository

import com.moodreel.core.database.dao.LibraryDao
import com.moodreel.core.domain.repository.LibraryRepository
import com.moodreel.core.model.LibraryItem
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaType
import com.moodreel.data.library.mapper.toDomain
import com.moodreel.data.library.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LibraryRepositoryImpl @Inject constructor(private val libraryDao: LibraryDao) : LibraryRepository {
    override fun observeByStatus(status: LibraryStatus): Flow<List<LibraryItem>> {
        return libraryDao.observeStatus(status)
            .map { list -> list.map { it.toDomain() } }
    }

    override fun observeStatus(
        mediaId: Int,
        mediaType: MediaType
    ): Flow<LibraryStatus?> {
        return libraryDao.observeItem(mediaId, mediaType)
            .map { item -> item?.status }
    }

    override suspend fun save(item: LibraryItem) {
        libraryDao.upsert(item.toEntity())
    }

    override suspend fun delete(mediaId: Int, mediaType: MediaType) {
        libraryDao.delete(mediaId, mediaType)
    }
}
