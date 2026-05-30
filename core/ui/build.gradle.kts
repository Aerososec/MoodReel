plugins {
    alias(libs.plugins.moodreel.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.moodreel.core.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Зависим от designsystem
    implementation(project(":core:designsystem"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material.icons.extended)
}