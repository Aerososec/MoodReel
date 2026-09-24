package com.moodreel.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.moodreel.feature.library.presentation.components.LibraryScreen
import com.moodreel.feature.search.presentation.SearchScreen

@Composable
fun MoodReelNavDisplay(modifier: Modifier) {
    var currentTab by rememberSaveable { mutableStateOf(MoodReelTab.SEARCH) }

    val libraryBackStack = rememberNavBackStack(LibraryKey)
    val searchBackStack = rememberNavBackStack(SearchKey)

    val backStack = when (currentTab) {
        MoodReelTab.SEARCH -> {
            searchBackStack
        }

        MoodReelTab.LIBRARY -> {
            libraryBackStack
        }
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            MoodReelBottomBar(
                currentTab = currentTab,
                onTabSelected = { tab ->
                    if (currentTab == tab) {
                        while (backStack.size > 1)
                            backStack.removeLastOrNull()
                    } else {
                        currentTab = tab
                    }
                },
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = modifier.padding(innerPadding),
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
                entry<LibraryKey> {
                    LibraryScreen(
                        onItemClick = { id, type ->
                            backStack.add(MediaDetailsKey(mediaId = id.value, mediaType = type))
                        },
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
}
