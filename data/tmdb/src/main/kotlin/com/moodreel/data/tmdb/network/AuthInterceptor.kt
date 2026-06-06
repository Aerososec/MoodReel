package com.moodreel.data.tmdb.network

import com.moodreel.data.tmdb.TmdbApiTokenProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val apiTokenProvider: TmdbApiTokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val authorizedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${apiTokenProvider.getToken()}")
            .header("Accept", "application/json")
            .build()

        return chain.proceed(authorizedRequest)
    }
}
