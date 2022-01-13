plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID

        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        vectorDrawables {
            useSupportLibrary = true
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(platform(project(":constraints")))
    kapt(platform(project(":constraints")))

    /////////////////////////////////////////////////////////////////////

    // region Application

    // AndroidX

    debugImplementation(Libraries.COMPOSE_UI_TOOLING)
    implementation(Libraries.ACTIVITY_COMPOSE)
    implementation(Libraries.COMPOSE_UI)
    implementation(Libraries.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Libraries.CORE_KTX)
    implementation(Libraries.MATERIAL)
    implementation(Libraries.MATERIAL_3)
    implementation(Libraries.NAVIGATION_COMPOSE)

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Unit Tests

    testImplementation(Libraries.JUNIT)

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region UI/Integration/E2E Tests

    androidTestImplementation(Libraries.COMPOSE_TEST)
    debugImplementation(Libraries.COMPOSE_MANIFEST_TEST)

    // endregion
}
