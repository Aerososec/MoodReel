pluginManagement {

    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MoodReel"
include(":app")
// Core modules
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":core:ui")
include(":data:tmdb")
include(":core:domain")
include(":feature:search")
include(":feature:details")

// Feature modules
include(":feature:home")
include(":core:database")
