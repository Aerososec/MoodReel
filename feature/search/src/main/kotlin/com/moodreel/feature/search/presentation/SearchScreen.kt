package com.moodreel.feature.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import com.moodreel.feature.search.presentation.components.SearchContent
import com.moodreel.feature.search.presentation.components.SearchTopBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToDetails: (MediaId, MediaType) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { effect ->
            when (effect) {
                is SearchSideEffect.NavigateToDetails -> {
                    onNavigateToDetails(effect.id, effect.type)
                }

                is SearchSideEffect.ShowError -> {
                    snackBarHostState.showSnackbar(effect.errorMessage)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchTopBar(
                title = uiState.title,
                onTitleChange = { viewModel.handleIntent(SearchIntent.TitleChanged(it)) },
                onClearClick = { viewModel.handleIntent(SearchIntent.ClearTitle) }
            )
            SearchContent(
                state = uiState,
                onMovieClick = { viewModel.handleIntent(SearchIntent.MovieClicked(it.id, it.mediaType)) },
                onRetry = { viewModel.handleIntent(SearchIntent.RetryClicked) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
