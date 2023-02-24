plugins {
    id("com.android.application") version Versions.ANDROID_APPLICATION_PLUGIN apply false
    id("com.android.library") version Versions.ANDROID_LIBRARY_PLUGIN apply false
    id("com.github.ben-manes.versions") version Versions.GRADLE_PLUGIN_VERSIONS
    id("com.google.dagger.hilt.android") version Versions.DAGGER apply false
    id("org.jetbrains.dokka") version Versions.KOTLIN
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}
