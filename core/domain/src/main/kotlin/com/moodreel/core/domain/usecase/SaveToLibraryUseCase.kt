package com.moodreel.core.domain.usecase

import com.moodreel.core.domain.repository.LibraryRepository
import com.moodreel.core.model.LibraryItem
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MovieDetails
import javax.inject.Inject

class SaveToLibraryUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(movieDetails: MovieDetails, status: LibraryStatus) {
        val libraryItem = LibraryItem(
            mediaId = movieDetails.id,
            mediaType = movieDetails.mediaType,
            title = movieDetails.title,
            posterUrl = movieDetails.posterUrl,
            releaseYear = movieDetails.releaseYear,
            status = status,
            addedAt = System.currentTimeMillis()
        )

        libraryRepository.save(libraryItem)
    }
}
