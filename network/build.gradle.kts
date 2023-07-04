@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "mz.co.bilheteira.network"
}

dependencies {
    testImplementation(libs.bundles.retrofit)
}