plugins {
    alias(libs.plugins.moodreel.android.library)
    alias(libs.plugins.moodreel.android.hilt)
}

android {
    namespace = "com.moodreel.data.library"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:database"))

    implementation(libs.bundles.coroutines)
}