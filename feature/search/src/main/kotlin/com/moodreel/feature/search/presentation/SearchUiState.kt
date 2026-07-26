package com.moodreel.feature.search.presentation

import androidx.compose.runtime.Immutable
import com.moodreel.core.model.Movie

@Immutable
data class SearchUiState(
    val title: String = "",
    val isLoading: Boolean = false,
    val results: List<Movie> = emptyList<Movie>(),
    val error: SearchError? = null
)
