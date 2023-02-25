plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK
    namespace = "${Config.APPLICATION_ID}.sample"

    defaultConfig {
        applicationId = "${Config.APPLICATION_ID}.sample"

        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

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
            isMinifyEnabled = false
            isShrinkResources = false
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(platform(project(":constraints")))
    api(project(":multimodal"))
    kapt(platform(project(":constraints")))

    /////////////////////////////////////////////////////////////////////

    // region Application

    debugImplementation(Libraries.COMPOSE_MANIFEST_TEST)
    debugImplementation(Libraries.COMPOSE_UI_TOOLING)

    implementation(Libraries.ACTIVITY_COMPOSE)
    implementation(Libraries.COMPOSE_UI)
    implementation(Libraries.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Libraries.CORE_KTX)
    implementation(Libraries.DAGGER_ANDROID)
    implementation(Libraries.MATERIAL)
    implementation(Libraries.MATERIAL_3)
    implementation(Libraries.NAVIGATION_COMPOSE)
    implementation(Libraries.WINDOW_MANAGER)

    kapt(Libraries.DAGGER_COMPILER)

    // endregion
}

kapt {
    correctErrorTypes = true
}
