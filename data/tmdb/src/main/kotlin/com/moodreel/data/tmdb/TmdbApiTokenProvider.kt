package com.moodreel.data.tmdb

interface TmdbApiTokenProvider {
    fun getToken(): String
}
