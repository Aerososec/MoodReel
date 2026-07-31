package com.moodreel.feature.details.presentation

import androidx.compose.runtime.Immutable

@Immutable
sealed interface DetailsError {
    data object NoInternet : DetailsError
    data object NotFound : DetailsError
    data class Server(val code: Int) : DetailsError
    data class Unknown(val message: String) : DetailsError
}
