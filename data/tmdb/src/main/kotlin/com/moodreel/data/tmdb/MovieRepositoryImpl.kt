package com.moodreel.data.tmdb

import com.moodreel.core.common.error.AppException
import com.moodreel.core.domain.repository.MovieRepository
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.Movie
import com.moodreel.core.model.MovieDetails
import com.moodreel.data.tmdb.mapper.toDomain
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(private val tmdbApi: TmdbApi) : MovieRepository {
    override suspend fun searchMovies(
        title: String,
        page: Int
    ): List<Movie> {
        executeNetworkCall {
            return tmdbApi.searchMulti(query = title, page = page).toDomain()
        }
    }

    override suspend fun getMovieDetails(id: MediaId): MovieDetails {
        executeNetworkCall {
            return tmdbApi.getMovieDetails(movieId = id.value).toDomain()
        }
    }

    override suspend fun getTvShowDetails(id: MediaId): MovieDetails {
        executeNetworkCall {
            return tmdbApi.getTvShowDetails(tvId = id.value).toDomain()
        }
    }

    private inline fun <T> executeNetworkCall(block: () -> T): T =
        try {
            block()
        } catch (e: IOException) {
            throw AppException.NoInternet(cause = e)
        } catch (e: HttpException) {
            throw AppException.Server(code = e.code(), message = e.message(), cause = e)
        } catch (e: SerializationException) {
            throw AppException.Parsing(cause = e)
        } catch (@Suppress("TooGenericExceptionCaught")e: Exception) {
            throw AppException.Unknown(message = e.message, cause = e)
        }
}
