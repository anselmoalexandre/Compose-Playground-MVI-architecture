@Suppress("DSL_SCOPE_VIOLATION") // Refer to issue #22797(https://github.com/gradle/gradle/issues/22797) for more info.
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "mz.co.bilheteira.storage"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }
}

dependencies {
    implementation(libs.bundles.room)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.truth)

    testImplementation(libs.bundles.test)
}