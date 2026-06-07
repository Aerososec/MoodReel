package com.moodreel.core.domain.usecase

import com.moodreel.core.domain.repository.MovieRepository
import com.moodreel.core.model.Movie
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(title: String, page: Int = 1): List<Movie> {
        if (title.isBlank()) return emptyList()
        return movieRepository.searchMovies(title, page)
    }
}
