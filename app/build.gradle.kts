import com.oliverspryn.gradle.BuildConfig

plugins {
    alias(libs.plugins.multimodal.app)

    alias(libs.plugins.multimodal.compose)
    alias(libs.plugins.multimodal.hilt)
}

android {
    namespace = "${BuildConfig.App.NAMESPACE}.sample" // Overrides the namespace from the application convention plugin
}

dependencies {
    implementation(project(":multimodal"))

    implementation(libs.material)
}
