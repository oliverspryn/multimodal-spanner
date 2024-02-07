package com.oliverspryn.gradle.plugin

import com.oliverspryn.gradle.extension.findLibraryId
import com.oliverspryn.gradle.extension.findPluginId
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("hilt"))
                apply(libs.findPluginId("ksp"))
            }

            dependencies {
                add("implementation", libs.findLibraryId("hilt-android"))
                add("implementation", libs.findLibraryId("hilt-navigation-compose"))
                add("ksp", libs.findLibraryId("hilt-compiler"))
            }
        }
    }
}
