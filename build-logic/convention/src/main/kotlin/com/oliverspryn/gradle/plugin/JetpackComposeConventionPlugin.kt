package com.oliverspryn.gradle.plugin

import com.oliverspryn.gradle.BuildConfig
import com.oliverspryn.gradle.extension.android
import com.oliverspryn.gradle.extension.library
import com.oliverspryn.gradle.extension.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JetpackComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            android {
                buildFeatures {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = version("compose-compiler")
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }

            dependencies {
                val compose = library("compose-bom")
                add("implementation", platform(compose))

                // Runtime
                add("implementation", library("activity-compose"))
                add("implementation", library("material3"))
                add("implementation", library("navigation"))
                add("implementation", library("ui"))
                add("implementation", library("ui-graphics"))
                add("implementation", library("ui-tooling-preview"))

                // Required to include these dependencies for each build type
                BuildConfig.App.BUILD_TYPES.forEach { variant ->
                    add("${variant.name}Implementation", library("ui-test-manifest"))
                    add("${variant.name}Implementation", library("ui-tooling"))
                }

                // Instrumentation
                add("androidTestImplementation", platform(compose))

                add("androidTestImplementation", library("ui-test-junit4"))
                add("androidTestImplementation", library("ui-test-manifest"))
            }
        }
    }
}
