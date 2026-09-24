package com.moodreel.feature.library.presentation.components

import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moodreel.core.model.LibraryStatus

@Composable
internal fun LibraryTabs(
    selectedStatus: LibraryStatus,
    plannedCount: Int,
    watchedCount: Int,
    onTabSelected: (LibraryStatus) -> Unit,
    modifier: Modifier = Modifier,
) {
    PrimaryTabRow(
        selectedTabIndex = LibraryStatus.entries.indexOf(selectedStatus),
        modifier = modifier,
    ) {
        LibraryStatus.entries.forEach { status ->
            val count = when (status) {
                LibraryStatus.PLANNED -> plannedCount
                LibraryStatus.WATCHED -> watchedCount
            }
            Tab(
                selected = status == selectedStatus,
                onClick = { onTabSelected(status) },
                text = { Text(text = "${status.tabLabel()} ($count)") },
            )
        }
    }
}

private fun LibraryStatus.tabLabel(): String = when (this) {
    LibraryStatus.PLANNED -> "Want to watch"
    LibraryStatus.WATCHED -> "Watched"
}
