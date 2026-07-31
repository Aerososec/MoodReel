package com.moodreel.feature.details.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moodreel.core.ui.topbar.MoodReelTopBar

@Composable
fun DetailsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MoodReelTopBar(title = "Details", onNavigationClick = onBackClick) },
    ) { innerPadding ->
        DetailsContent(
            state = uiState,
            onRetry = { viewModel.handleIntent(DetailsIntent.RetryClicked) },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}
