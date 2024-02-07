package com.oliverspryn.gradle.plugin

import com.android.build.api.dsl.LibraryExtension
import com.oliverspryn.gradle.extension.configureCompose
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

open class BaseComposeLibraryConventionPlugin : BaseComposeConventionPlugin() {
    override fun apply(target: Project) {
        super.apply(target)

        with(target) {
            extensions.configure<LibraryExtension> {
                configureCompose(this, libs)
            }
        }
    }
}
