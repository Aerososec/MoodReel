package com.moodreel.core.domain.usecase

import com.moodreel.core.domain.repository.MovieRepository
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import com.moodreel.core.model.MovieDetails
import javax.inject.Inject

class GetMediaDetailsUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(id: MediaId, type: MediaType): MovieDetails {
        return repository.getDetails(id = id, type = type)
    }
}
