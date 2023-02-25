import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application") version Versions.ANDROID_APPLICATION_PLUGIN apply false
    id("com.android.library") version Versions.ANDROID_LIBRARY_PLUGIN apply false
    id("com.github.ben-manes.versions") version Versions.GRADLE_PLUGIN_VERSIONS
    id("com.google.dagger.hilt.android") version Versions.DAGGER apply false
    id("org.jetbrains.dokka") version Versions.DOKKA
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    rejectVersionIf {
        isNonStable(candidate.version)
    }

    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}
