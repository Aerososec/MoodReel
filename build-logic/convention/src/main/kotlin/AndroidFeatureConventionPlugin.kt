import com.android.build.gradle.LibraryExtension
import com.moodreel.buildlogic.configureAndroidCompose
import com.moodreel.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Применяем базовый library-плагин
            pluginManager.apply("moodreel.android.library")
            // Применяем Hilt-плагин (создадим следующим)
            pluginManager.apply("moodreel.android.hilt")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            // Настраиваем Compose
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

            dependencies {
                // Все feature-модули используют общие штуки
                "implementation"(libs.findBundle("lifecycle").get())
                "implementation"(libs.findBundle("coroutines").get())
                "implementation"(libs.findLibrary("hilt-navigation-compose").get())

                // Тесты
                "testImplementation"(libs.findBundle("testing-unit").get())
                "androidTestImplementation"(libs.findBundle("testing-compose").get())
            }
        }
    }
}