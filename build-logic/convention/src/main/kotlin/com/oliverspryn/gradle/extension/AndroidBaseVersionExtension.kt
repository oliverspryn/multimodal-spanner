package com.oliverspryn.gradle.extension

import com.android.build.api.dsl.CommonExtension
import com.oliverspryn.gradle.BuildConfig

internal fun configureAndroidBaseVersion(commonExtension: CommonExtension<*, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = BuildConfig.Android.COMPILE_SDK

        defaultConfig {
            minSdk = BuildConfig.Android.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }
}
