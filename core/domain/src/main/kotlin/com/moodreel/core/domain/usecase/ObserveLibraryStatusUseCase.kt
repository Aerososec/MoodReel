package com.moodreel.core.domain.usecase

import com.moodreel.core.domain.repository.LibraryRepository
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLibraryStatusUseCase @Inject constructor(private val libraryRepository: LibraryRepository) {
    operator fun invoke(mediaId: MediaId, mediaType: MediaType): Flow<LibraryStatus?> {
        return libraryRepository.observeStatus(mediaId.value, mediaType)
    }
}
