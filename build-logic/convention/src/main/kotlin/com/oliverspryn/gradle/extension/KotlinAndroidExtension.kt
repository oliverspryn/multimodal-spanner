package com.oliverspryn.gradle.extension

import com.android.build.api.dsl.CommonExtension
import com.oliverspryn.gradle.BuildConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileOptions {
            sourceCompatibility = BuildConfig.Jvm.VERSION
            targetCompatibility = BuildConfig.Jvm.VERSION
        }
    }

    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = BuildConfig.Jvm.KOTLIN_WARNINGS_AS_ERRORS
            jvmTarget = BuildConfig.Jvm.VERSION_STRING
        }
    }
}
