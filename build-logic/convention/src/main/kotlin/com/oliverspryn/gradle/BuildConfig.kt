package com.oliverspryn.gradle

import org.gradle.api.JavaVersion

object BuildConfig {
    private object DeveloperCoreConfig {
        // region App Version

        // ==================================================================
        // Hey developers! This is where you can configure the app's version.
        // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        const val VERSION_CODE = 1
        const val VERSION_NAME = CentralRepository.Artifact.VERSION

        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        // Make sure to update both of these values before publishing.
        // ==================================================================

        // endregion

        // region SDKs and Supported Android API Versions

        const val COMPILE_SDK = 34
        const val MIN_SDK = 21
        const val TARGET_SDK = COMPILE_SDK

        // endregion

        // region Build Types and Common Build Config Fields

        val BUILD_TYPES = listOf(
            BuildType.Debug,
            BuildType.Release
        )

        // endregion
    }

    // =============================================================================================
    // Everything from above is meant for you to easily configure the app.
    // Your settings from above will percolate down to the rest of the config below.
    //
    // There really shouldn't be any need to change anything below this line.
    // If you do, you're probably changing some of the more fundamental aspects of the app.
    //
    // So, I guess the correct phrases would be:
    //   - "Fool of a Took!" ~ Gandalf
    //   - "Here be dragons." ~ Unknown
    //   - "Live long and prosper." ~ Spock
    //   - "May the force be with you." ~ Obi-Wan Kenobi
    //   - "I have a bad feeling about this." ~ Han Solo
    // =============================================================================================

    object App {
        const val APPLICATION_ID = "com.oliverspryn.android.multimodal"

        val BUILD_TYPES = DeveloperCoreConfig.BUILD_TYPES

        /**
         * This is effectively the base package name for the application. This
         * should not be confused with the [APPLICATION_ID], which is used to
         * uniquely identify the application on the device and in the Google Play
         * Store. This identifier was set years ago and cannot now be changed.
         */
        const val NAMESPACE = APPLICATION_ID

        const val VERSION_CODE = DeveloperCoreConfig.VERSION_CODE
        const val VERSION_NAME = DeveloperCoreConfig.VERSION_NAME
    }

    object Android {
        const val COMPILE_SDK = DeveloperCoreConfig.COMPILE_SDK
        const val MIN_SDK = DeveloperCoreConfig.MIN_SDK
        const val TARGET_SDK = DeveloperCoreConfig.TARGET_SDK
    }

    object Jvm {
        const val KOTLIN_WARNINGS_AS_ERRORS = true
        val VERSION = JavaVersion.VERSION_1_8
        const val VERSION_STRING = "1.8"
    }

    abstract class BuildType(
        val enableUnitTestCoverage: Boolean = true,
        val initWithOtherPreExistingBuildType: String? = "debug",
        val isBuiltInBuildType: Boolean = false,
        val isDebuggable: Boolean = true,
        val isMinifyEnabled: Boolean = false,
        val isShrinkResources: Boolean = false,
        val name: String
    ) {
        object Debug : BuildType(
            isBuiltInBuildType = true,
            name = "debug"
        )

        object Release : BuildType(
            enableUnitTestCoverage = false,
            isBuiltInBuildType = true,
            name = "release"
        )
    }
}
