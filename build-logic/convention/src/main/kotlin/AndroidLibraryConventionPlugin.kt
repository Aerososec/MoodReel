import com.android.build.gradle.LibraryExtension
import com.moodreel.buildlogic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("moodreel.detekt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                // targetSdk убран — в library-модулях он не нужен в AGP 9.0+
                // При необходимости настраиваем testOptions.targetSdk здесь
            }
        }
    }
}