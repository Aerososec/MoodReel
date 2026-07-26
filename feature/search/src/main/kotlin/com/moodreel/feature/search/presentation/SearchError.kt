package com.moodreel.feature.search.presentation

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SearchError {
    data object NoInternet : SearchError
    data class Server(val code: Int) : SearchError
    data class Unknown(val errorMessage: String) : SearchError
}
