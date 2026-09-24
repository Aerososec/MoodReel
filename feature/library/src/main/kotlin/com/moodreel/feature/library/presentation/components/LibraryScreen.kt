package com.moodreel.feature.library.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import com.moodreel.feature.library.presentation.LibraryIntent
import com.moodreel.feature.library.presentation.LibraryViewModel

@Composable
fun LibraryScreen(
    onItemClick: (MediaId, MediaType) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LibraryTabs(
                selectedStatus = uiState.selectedStatus,
                plannedCount = uiState.planned.size,
                watchedCount = uiState.watched.size,
                onTabSelected = { status ->
                    viewModel.handleIntent(LibraryIntent.TabSelected(status))
                },
            )
            LibraryContent(
                state = uiState,
                onItemClick = onItemClick,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
