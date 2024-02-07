package com.oliverspryn.gradle.plugin

import com.oliverspryn.gradle.extension.findPluginId
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BaseAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("kotlin-android"))
            }
        }
    }
}
