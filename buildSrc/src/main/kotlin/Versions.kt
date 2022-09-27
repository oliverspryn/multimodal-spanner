import org.gradle.api.JavaVersion

object Versions {
    // region App & Build Tool definitions

    const val COMPILE_SDK = 31
    const val MIN_SDK = 21
    const val TARGET_SDK = 31

    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.1"

    // endregion

    // region Core Libraries

    const val ANDROID_APPLICATION_PLUGIN = "7.2.2"
    const val ANDROID_LIBRARY_PLUGIN = "7.2.2"
    const val COMPOSE = "1.1.0-beta01"
    val JVM = JavaVersion.VERSION_11
    val JVM_STRING = JavaVersion.VERSION_11.toString()
    const val KOTLIN = "1.5.31"

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Application

    const val ACTIVITY_COMPOSE = "1.4.0"
    const val CORE_KTX = "1.7.0"
    const val MATERIAL = "1.6.0-alpha01"
    const val MATERIAL_3 = "1.0.0-alpha02"
    const val NAVIGATION_COMPOSE = "2.4.0-beta02"
    const val WINDOW_MANAGER = "1.0.0-beta04"

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Unit Tests

    const val JUNIT = "4.13.2"

    // endregion
}
