import org.jetbrains.dokka.gradle.DokkaTaskPartial

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

apply(plugin = "org.jetbrains.dokka")

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = Versions.JVM
        targetCompatibility = Versions.JVM
    }

    kotlinOptions {
        jvmTarget = Versions.JVM_STRING
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
}

dependencies {
    api(platform(project(":constraints")))
    kapt(platform(project(":constraints")))

    /////////////////////////////////////////////////////////////////////

    // region Application

    implementation(Libraries.ACTIVITY_COMPOSE)
    implementation(Libraries.COMPOSE_UI_UNIT)
    implementation(Libraries.WINDOW_MANAGER)

    // endregion
}

tasks.withType<DokkaTaskPartial>().configureEach {
    moduleName.set(Config.PROJECT_NAME)
    moduleVersion.set("1.0.0")
}
