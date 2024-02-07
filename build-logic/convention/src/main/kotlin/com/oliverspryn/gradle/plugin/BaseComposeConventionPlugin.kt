package com.oliverspryn.gradle.plugin

import com.oliverspryn.gradle.extension.findLibraryId
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

abstract class BaseComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                val compose = libs.findLibraryId("compose-bom")
                add("implementation", platform(compose))
            }
        }
    }
}
