@Suppress("DSL_SCOPE_VIOLATION") // Refer to issue #22797(https://github.com/gradle/gradle/issues/22797) for more info.
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace= "mz.co.bilheteira.statemanager"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.bundles.kotlin.coroutines)
    testImplementation(libs.bundles.test)
}