plugins {
    alias(libs.plugins.moodreel.android.feature)
}

android {
    namespace = "com.moodreel.feature.details"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))

    implementation(libs.bundles.coroutines)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}