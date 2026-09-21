package com.moodreel.core.domain.repository

import com.moodreel.core.model.LibraryItem
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaType
import kotlinx.coroutines.flow.Flow

interface LibraryRepository {
    fun observeByStatus(status: LibraryStatus): Flow<List<LibraryItem>>

    fun observeStatus(mediaId: Int, mediaType: MediaType): Flow<LibraryStatus?>

    suspend fun save(item: LibraryItem)

    suspend fun delete(mediaId: Int, mediaType: MediaType)
}
