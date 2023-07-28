@Suppress("DSL_SCOPE_VIOLATION") // Refer to issue #22797(https://github.com/gradle/gradle/issues/22797) for more info.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    kotlin("kapt")
}

android {
    namespace = "mz.co.bilheteira.statemachine"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "mz.co.bilheteira.statemachine"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.minSdk.get().toInt()

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

configurations {
//    implementation.configure {
//        this.exclude(group = "org.jetbrains", module = "annotations")
//    }
}

dependencies {
    implementation(project(path = ":domain"))
    implementation(project(path = ":state-manager"))

    implementation(libs.core.ktx)

    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.activity.compose)

    implementation(libs.bundles.compose)

    implementation(libs.timber)

    implementation(libs.bundles.coil)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(libs.bundles.test)
}