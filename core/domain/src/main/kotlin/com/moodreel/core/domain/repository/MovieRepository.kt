package com.moodreel.core.domain.repository

import com.moodreel.core.model.MediaId
import com.moodreel.core.model.Movie
import com.moodreel.core.model.MovieDetails

interface MovieRepository {
    suspend fun searchMovies(title: String, page: Int = 1): List<Movie>

    suspend fun getMovieDetails(id: MediaId): MovieDetails

    suspend fun getTvShowDetails(id: MediaId): MovieDetails
}
