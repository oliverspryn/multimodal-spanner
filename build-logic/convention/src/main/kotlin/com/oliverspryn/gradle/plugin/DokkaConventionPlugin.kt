package com.oliverspryn.gradle.plugin

import com.oliverspryn.gradle.CentralRepositoryConfig
import com.oliverspryn.gradle.extension.plugin
import com.oliverspryn.gradle.extension.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTaskPartial

class DokkaConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins {
                apply(plugin("dokka"))
            }

            tasks.withType<DokkaTaskPartial>().configureEach {
                moduleName.set(CentralRepositoryConfig.LIBRARY_NAME)
                moduleVersion.set(CentralRepositoryConfig.Artifact.VERSION)
            }
        }
    }
}
