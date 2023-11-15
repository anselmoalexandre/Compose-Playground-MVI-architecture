@Suppress("DSL_SCOPE_VIOLATION") // Refer to issue #22797(https://github.com/gradle/gradle/issues/22797) for more info.
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "mz.co.bilheteira.database"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility= JavaVersion.VERSION_17
        targetCompatibility= JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.truth)

    testImplementation(libs.bundles.test)
}