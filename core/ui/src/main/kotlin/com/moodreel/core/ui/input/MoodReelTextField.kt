package com.moodreel.core.ui.input

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme

@Composable
fun MoodReelTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    label: String? = null,
    singleLine: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = placeholder?.let {
            {
                Text(text = it, style = MoodReelTheme.typography.bodyLarge)
            }
        },
        label = label?.let {
            {
                Text(text = it, style = MoodReelTheme.typography.labelMedium)
            }
        },
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(MoodReelTheme.radius.md),
        textStyle = MoodReelTheme.typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MoodReelTheme.colors.primary,
            unfocusedBorderColor = MoodReelTheme.colors.outline,
            focusedLabelColor = MoodReelTheme.colors.primary,
            cursorColor = MoodReelTheme.colors.primary,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun MoodReelTextFieldPreview() {
    MoodReelTheme {
        MoodReelTextField(
            value = "Inception",
            onValueChange = {},
            placeholder = "Поиск фильмов",
            label = "Название",
            singleLine = true,
            modifier = Modifier.padding(MoodReelTheme.spacing.lg),
        )
    }
}
