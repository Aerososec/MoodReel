package com.moodreel.feature.details.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.model.LibraryStatus

@Composable
internal fun LibraryStatusSelector(
    currentStatus: LibraryStatus?,
    onStatusSelected: (LibraryStatus) -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.xs),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.sm)) {
            LibraryStatus.entries.forEach { status ->
                val isSelected = status == currentStatus
                FilterChip(
                    selected = isSelected,
                    onClick = { if (!isSelected) onStatusSelected(status) },
                    label = { Text(text = status.label()) },
                    leadingIcon = if (isSelected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize),
                            )
                        }
                    } else {
                        null
                    },
                )
            }
        }

        AnimatedVisibility(visible = currentStatus != null) {
            TextButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
                Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                Text(text = "Remove from library")
            }
        }
    }
}

private fun LibraryStatus.label(): String = when (this) {
    LibraryStatus.PLANNED -> "Want to watch"
    LibraryStatus.WATCHED -> "Watched"
}
