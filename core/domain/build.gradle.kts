plugins {
    alias(libs.plugins.moodreel.jvm.library)
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.bundles.coroutines)
    implementation(libs.javax.inject)

    testImplementation(libs.bundles.testing.unit)
}