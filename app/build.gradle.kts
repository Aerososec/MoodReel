import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.moodreel.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.moodreel.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

// Загружаем local.properties в начале файла
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(FileInputStream(localPropertiesFile))
    }
}

// Получаем токен. Если в local.properties его нет — читаем из env (для CI)
val tmdbApiToken: String = localProperties.getProperty("TMDB_API_TOKEN")
    ?: System.getenv("TMDB_API_TOKEN")
    ?: ""

android {
    namespace = "com.moodreel.app"

    defaultConfig {
        applicationId = "com.moodreel.app"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TMDB_API_TOKEN", "\"$tmdbApiToken\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Базовый Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material.icons.extended)

    // Тесты
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Навигация
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.navigation.compose)

    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":data:tmdb"))
    implementation(project(":feature:search"))
    implementation(project(":feature:details"))
    implementation(project(":data:library"))
}