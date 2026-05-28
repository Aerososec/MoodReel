import com.moodreel.buildlogic.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.configure<DetektExtension> {
                // Общий конфиг для всех модулей лежит в корне проекта
                config.setFrom(rootProject.files("config/detekt/detekt.yml"))
                // Если правило не описано в нашем конфиге — берём дефолт detekt
                buildUponDefaultConfig = true
                // Автоформатирование (от detekt-formatting) — не падать, а чинить
                autoCorrect = true
            }

            dependencies {
                "detektPlugins"(libs.findLibrary("detekt-formatting").get())
            }

            // Настройка задач detekt
            tasks.withType<Detekt>().configureEach {
                jvmTarget = "17"
                reports {
                    html.required.set(true)   // человекочитаемый отчёт
                    xml.required.set(false)
                    txt.required.set(false)
                    sarif.required.set(true)  // для интеграции с GitHub
                    md.required.set(false)
                }
            }
        }
    }
}