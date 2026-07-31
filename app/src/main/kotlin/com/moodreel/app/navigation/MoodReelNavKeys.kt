package com.moodreel.app.navigation

import androidx.navigation3.runtime.NavKey
import com.moodreel.core.model.MediaType
import kotlinx.serialization.Serializable

@Serializable
data object SearchKey : NavKey

@Serializable
data class MediaDetailsKey(
    val mediaId: Int,
    val mediaType: MediaType
) : NavKey
