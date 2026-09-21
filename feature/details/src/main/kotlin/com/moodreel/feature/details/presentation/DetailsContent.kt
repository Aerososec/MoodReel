package com.moodreel.feature.details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.ui.state.MoodReelLoadingIndicator

@Composable
internal fun DetailsContent(
    state: DetailsUiState,
    onRetry: () -> Unit,
    onStatusSelected: (LibraryStatus) -> Unit,
    onRemoveFromLibrary: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        DetailsUiState.Loading -> MoodReelLoadingIndicator(modifier = modifier)
        is DetailsUiState.Content -> DetailsBody(
            details = state.content,
            libraryStatus = state.libraryStatus,
            onStatusSelected = onStatusSelected,
            onRemoveFromLibrary = onRemoveFromLibrary,
            modifier = modifier
        )
        is DetailsUiState.Error -> {
            onRetry()
        }
    }
}
