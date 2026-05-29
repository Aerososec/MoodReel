plugins {
    alias(libs.plugins.moodreel.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.moodreel.core.designsystem"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.compose.material.icons.extended)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation("androidx.compose.ui:ui-text-google-fonts:1.7.6")
}