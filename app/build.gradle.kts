plugins {
    alias(libs.plugins.multimodal.app)

    alias(libs.plugins.multimodal.compose.app)
    alias(libs.plugins.multimodal.hilt)
}

dependencies {
    implementation(project(":multimodal"))

    implementation(libs.material)
}
