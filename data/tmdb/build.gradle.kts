plugins {
    alias(libs.plugins.moodreel.android.library)
    alias(libs.plugins.moodreel.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.moodreel.data.tmdb"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "TMDB_BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "TMDB_IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/\"")
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.network)

    testImplementation(libs.bundles.testing.unit)
}