package com.oliverspryn.gradle.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalog

internal fun configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
    libs: VersionCatalog
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersionId("compose-compiler")
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}
