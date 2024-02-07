package com.oliverspryn.gradle.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.oliverspryn.gradle.BuildConfig
import com.oliverspryn.gradle.extension.configureCompose
import com.oliverspryn.gradle.extension.findLibraryId
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposeApplicationConventionPlugin : BaseComposeConventionPlugin() {
    override fun apply(target: Project) {
        super.apply(target)

        with(target) {
            extensions.configure<ApplicationExtension> {
                configureCompose(this, libs)
            }

            dependencies {
                add("implementation", libs.findLibraryId("activity-compose"))
                add("implementation", libs.findLibraryId("material3"))
                add("implementation", libs.findLibraryId("navigation"))
                add("implementation", libs.findLibraryId("ui"))
                add("implementation", libs.findLibraryId("ui-graphics"))
                add("implementation", libs.findLibraryId("ui-tooling-preview"))

                // Required to include these dependencies for each build type
                BuildConfig.App.BUILD_TYPES.forEach { variant ->
                    add("${variant.name}Implementation", libs.findLibraryId("ui-test-manifest"))
                    add("${variant.name}Implementation", libs.findLibraryId("ui-tooling"))
                }
            }
        }
    }
}
