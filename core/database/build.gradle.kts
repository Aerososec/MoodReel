plugins {
    alias(libs.plugins.moodreel.android.library)
    alias(libs.plugins.moodreel.android.hilt)
    alias(libs.plugins.moodreel.android.room)
}

android {
    namespace = "com.moodreel.core.database"
}

dependencies {
    implementation(project(":core:model"))
}