plugins {
    alias(libs.plugins.moodreel.android.feature)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.moodreel.feature.home"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}