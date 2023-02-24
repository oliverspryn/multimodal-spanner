import org.gradle.api.JavaVersion

object Versions {
    // region App & Build Tool definitions

    const val COMPILE_SDK = 33
    const val MIN_SDK = 21
    const val TARGET_SDK = 33

    const val VERSION_CODE = 1
    const val VERSION_NAME = CentralRepository.Artifact.VERSION

    // endregion

    // region Core Libraries

    const val ANDROID_APPLICATION_PLUGIN = "7.2.2"
    const val ANDROID_LIBRARY_PLUGIN = "7.2.2"
    const val COMPOSE = "1.2.1"            // Reference this list for a the latest version:
    const val COMPOSE_COMPILER = "1.3.1"   // https://developer.android.com/jetpack/androidx/releases/compose
    const val DAGGER = "2.44"
    val JVM = JavaVersion.VERSION_1_8
    const val JVM_STRING = "1.8"
    const val KOTLIN = "1.7.10"

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Application

    const val ACTIVITY_COMPOSE = "1.6.0"
    const val CORE_KTX = "1.9.0"
    const val MATERIAL = "1.8.0-alpha01"
    const val MATERIAL_3 = "1.0.0-beta03"
    const val NAVIGATION_COMPOSE = "2.6.0-alpha01"
    const val WINDOW_MANAGER = "1.1.0-alpha03"

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Gradle tooling

    const val GRADLE_PLUGIN_VERSIONS = "0.42.0"

    // endregion
}
