package com.oliverspryn.gradle.plugin

import com.android.build.api.dsl.LibraryBuildType
import com.android.build.api.dsl.LibraryExtension
import com.oliverspryn.gradle.BuildConfig
import com.oliverspryn.gradle.extension.configureAndroidBaseVersion
import com.oliverspryn.gradle.extension.configureKotlinAndroid
import com.oliverspryn.gradle.extension.findPluginId
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : BaseAndroidConventionPlugin() {
    override fun apply(target: Project) {
        super.apply(target)

        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("android-library"))
            }

            extensions.configure<LibraryExtension> {
                configureAndroidBaseVersion(this)
                configureKotlinAndroid(this)

                namespace = BuildConfig.App.NAMESPACE

                defaultConfig {
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }

                buildTypes {
                    BuildConfig.App.BUILD_TYPES.forEach { buildTypeProperties ->
                        if (buildTypeProperties.isBuiltInBuildType) {
                            getByName(buildTypeProperties.name) {
                                setupBuildType(
                                    buildTypes = this@buildTypes,
                                    applicationBuildType = this,
                                    buildTypeProperties = buildTypeProperties
                                )
                            }
                        } else {
                            create(buildTypeProperties.name) {
                                setupBuildType(
                                    buildTypes = this@buildTypes,
                                    applicationBuildType = this,
                                    buildTypeProperties = buildTypeProperties
                                )
                            }
                        }
                    }
                }

                buildFeatures {
                    buildConfig = true
                }
            }
        }
    }

    private fun setupBuildType(
        buildTypes: NamedDomainObjectContainer<LibraryBuildType>,
        applicationBuildType: LibraryBuildType,
        buildTypeProperties: BuildConfig.BuildType
    ) = with(applicationBuildType) {
        buildTypeProperties.initWithOtherPreExistingBuildType?.let {
            initWith(buildTypes.getByName(it))
        }

        enableUnitTestCoverage = buildTypeProperties.enableUnitTestCoverage
        isMinifyEnabled = buildTypeProperties.isMinifyEnabled
    }
}
