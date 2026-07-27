package com.moodreel.feature.search.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.model.Movie
import com.moodreel.feature.search.presentation.SearchUiState

@Composable
fun SearchContent(
    state: SearchUiState,
    onMovieClick: (Movie) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.error != null -> {
            SearchStates.Error(
                error = state.error,
                onRetry = onRetry,
                modifier = modifier
            )
        }

        state.isLoading -> {
            LoadingIndicator(modifier)
        }

        state.results.isNotEmpty() -> {
            MovieList(
                movies = state.results,
                onMovieClick = onMovieClick,
                modifier = modifier
            )
        }

        state.title.isNotBlank() -> {
            SearchStates.Initial(
                modifier = modifier
            )
        }

        else -> {
            SearchStates.NoResults(
                query = state.title,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MoodReelTheme.colors.primary
        )
    }
}

@Composable
private fun MovieList(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            vertical = MoodReelTheme.spacing.md,
            horizontal = MoodReelTheme.spacing.md
        )
    ) {
        items(
            items = movies,
            key = { movie -> movie.id.value }
        ) { movie ->
            MovieListItem(
                movie = movie
            ) {
                onMovieClick(movie)
            }
        }
    }
}
