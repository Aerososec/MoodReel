package com.moodreel.feature.search.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme

@Composable
fun SearchTopBar(
    title: String,
    onTitleChange: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MoodReelTheme.spacing.lg),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.sm)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search movies and TV") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = MoodReelTheme.colors.onSurfaceVariant
                )
            },
            trailingIcon = if (title.isNotEmpty()) {
                {
                    IconButton(onClick = onClearClick) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = "Clear",
                            tint = MoodReelTheme.colors.onSurfaceVariant
                        )
                    }
                }
            } else {
                null
            },
            singleLine = true,

        )
    }
}

@Preview
@Composable
private fun SearchTopBarPreview() {
    MoodReelTheme {
        SearchTopBar(
            title = "Inception",
            onTitleChange = {},
            onClearClick = {},
        )
    }
}
