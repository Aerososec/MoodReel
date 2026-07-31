package com.moodreel.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.moodreel.feature.details.presentation.DetailsScreen
import com.moodreel.feature.details.presentation.DetailsViewModel
import com.moodreel.feature.search.presentation.SearchScreen

@Composable
fun MoodReelNavDisplay(modifier: Modifier) {
    val backStack = rememberNavBackStack(SearchKey)

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            rememberViewModelStoreNavEntryDecorator<NavKey>(),
        ),
        entryProvider = entryProvider {
            entry<SearchKey> {
                SearchScreen(
                    onNavigateToDetails = { id, type ->
                        backStack.add(MediaDetailsKey(id.value, type))
                    }
                )
            }
            entry<MediaDetailsKey> { key ->
                val viewModel = hiltViewModel<DetailsViewModel, DetailsViewModel.Factory>(
                    creationCallback = { factory ->
                        factory.create(mediaId = key.mediaId, mediaType = key.mediaType)
                    },
                )
                DetailsScreen(
                    viewModel = viewModel,
                    onBackClick = { backStack.removeLastOrNull() },
                )
            }
        }
    )
}
