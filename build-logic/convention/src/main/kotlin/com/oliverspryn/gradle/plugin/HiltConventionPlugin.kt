package com.oliverspryn.gradle.plugin

import com.oliverspryn.gradle.extension.library
import com.oliverspryn.gradle.extension.plugin
import com.oliverspryn.gradle.extension.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins {
                apply(plugin("hilt"))
                apply(plugin("ksp"))
            }

            dependencies {
                add("implementation", library("hilt-android"))
                add("implementation", library("hilt-navigation-compose"))
                add("ksp", library("hilt-compiler"))

                add("testImplementation", library("hilt-android-testing"))
                add("kspTest", library("hilt-compiler"))

                add("androidTestImplementation", library("hilt-android-testing"))
                add("kspAndroidTest", library("hilt-compiler"))
            }
        }
    }
}
