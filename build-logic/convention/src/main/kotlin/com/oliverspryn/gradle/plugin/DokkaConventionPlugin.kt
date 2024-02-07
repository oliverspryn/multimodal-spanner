package com.oliverspryn.gradle.plugin

import com.oliverspryn.gradle.CentralRepository
import com.oliverspryn.gradle.extension.findPluginId
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTaskPartial

class DokkaConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("dokka"))
            }

            tasks.withType<DokkaTaskPartial>().configureEach {
                moduleName.set(CentralRepository.LIBRARY_NAME)
                moduleVersion.set(CentralRepository.Artifact.VERSION)
            }
        }
    }
}
