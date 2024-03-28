import com.oliverspryn.gradle.BuildConfig
import com.oliverspryn.gradle.extension.version

plugins {
    alias(libs.plugins.multimodal.library)

    alias(libs.plugins.multimodal.centralrepository)
    alias(libs.plugins.multimodal.dokka)
}

android {
    namespace = BuildConfig.App.NAMESPACE // Overrides the namespace from the library convention plugin

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = version("compose-compiler")
    }
}

dependencies {
    // Uses api() instead of implementation() to expose the libraries to the consumers
    api(libs.activity.compose)
    api(libs.ui.unit)
    api(libs.window)
}
