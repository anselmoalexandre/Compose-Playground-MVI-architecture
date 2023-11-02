@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "mz.co.bilheteira.domain"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(path = ":network"))
    implementation(project(path = ":storage"))

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.truth)

    testImplementation(libs.bundles.test)
}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}
