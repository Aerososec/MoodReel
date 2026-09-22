plugins {
    alias(libs.plugins.moodreel.android.feature)
}

android {
    namespace = "com.moodreel.feature.library"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}