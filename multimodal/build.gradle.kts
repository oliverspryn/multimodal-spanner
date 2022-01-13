plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        vectorDrawables {
            useSupportLibrary = true
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = true

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
}

dependencies {
    api(platform(project(":constraints")))
    kapt(platform(project(":constraints")))

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
