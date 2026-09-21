package com.moodreel.feature.details.presentation

import androidx.compose.runtime.Immutable
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MovieDetails

@Immutable
sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data class Content(val content: MovieDetails, val libraryStatus: LibraryStatus?) : DetailsUiState
    data class Error(val error: DetailsError) : DetailsUiState
}
