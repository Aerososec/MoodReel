package com.moodreel.app.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moodreel.core.model.MediaType
import com.moodreel.core.ui.button.MoodReelButton

@Composable
fun MediaDetailsPlaceholder(
    id: Int,
    type: MediaType,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("ID: $id")
        Text("Type: $type")
        MoodReelButton(
            text = "Back",
            onClick = onBack
        )
    }
}
