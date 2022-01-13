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

    const val ANDROID_APPLICATION_PLUGIN = "7.2.0-alpha03"
    const val ANDROID_LIBRARY_PLUGIN = "7.2.0-alpha03"
    const val COMPOSE = "1.1.0-beta01"
    val JVM = JavaVersion.VERSION_11
    val JVM_STRING = JavaVersion.VERSION_11.toString()
    const val KOTLIN = "1.6.10"

    // endregion

    /////////////////////////////////////////////////////////////////////

    // region Non-Core, Runtime Libraries

    const val ACCOMPANIST = "0.21.1-beta"
    const val ACTIVITY_COMPOSE = "1.4.0"
    const val COMPOSE_MATERIAL_3 = "1.0.0-alpha01"
    const val CORE_KTX = "1.7.0"
    const val LIFECYCLE_RUNTIME_KTX = "2.4.0"
    const val MATERIAL = "1.5.0-alpha05"

    // endregion
}
