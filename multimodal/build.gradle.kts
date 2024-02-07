plugins {
    alias(libs.plugins.multimodal.library)

    alias(libs.plugins.multimodal.compose.library.base)
    alias(libs.plugins.multimodal.dokka)
    alias(libs.plugins.multimodal.publish)
}

dependencies {
    // Uses api() instead of implementation() to expose the libraries to the consumers
    api(libs.activity.compose)
    api(libs.ui.unit)
    api(libs.window)
}
