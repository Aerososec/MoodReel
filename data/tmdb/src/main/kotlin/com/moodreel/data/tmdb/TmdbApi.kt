package com.moodreel.data.tmdb

import com.moodreel.data.tmdb.dto.MovieDetailsDto
import com.moodreel.data.tmdb.dto.SearchResponseDto
import com.moodreel.data.tmdb.dto.TvShowDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TmdbApi {
    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
        @Query("include_adult") includeAdult: Boolean = true,
    ): SearchResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
    ): MovieDetailsDto

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tvId: Int,
        @Query("language") language: String = "en-US",
    ): TvShowDetailsDto
}
