package com.moodreel.core.domain.usecase

import com.moodreel.core.domain.repository.LibraryRepository
import com.moodreel.core.model.LibraryItem
import com.moodreel.core.model.LibraryStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLibraryUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    operator fun invoke(status: LibraryStatus): Flow<List<LibraryItem>> {
        return libraryRepository.observeByStatus(status)
    }
}
