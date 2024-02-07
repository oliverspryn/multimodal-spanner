package com.oliverspryn.gradle.plugin

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.oliverspryn.gradle.BuildConfig
import com.oliverspryn.gradle.extension.configureAndroidBaseVersion
import com.oliverspryn.gradle.extension.configureKotlinAndroid
import com.oliverspryn.gradle.extension.findPluginId
import com.oliverspryn.gradle.extension.libs
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : BaseAndroidConventionPlugin() {
    override fun apply(target: Project) {
        super.apply(target)

        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("android-application"))
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidBaseVersion(this)
                configureKotlinAndroid(this)

                namespace = "${BuildConfig.App.NAMESPACE}.sample"

                defaultConfig {
                    applicationId = "${BuildConfig.App.APPLICATION_ID}.sample"
                    targetSdk = BuildConfig.Android.TARGET_SDK

                    versionCode = BuildConfig.App.VERSION_CODE
                    versionName = BuildConfig.App.VERSION_NAME

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
        buildTypes: NamedDomainObjectContainer<ApplicationBuildType>,
        applicationBuildType: ApplicationBuildType,
        buildTypeProperties: BuildConfig.BuildType
    ) = with(applicationBuildType) {
        buildTypeProperties.initWithOtherPreExistingBuildType?.let {
            initWith(buildTypes.getByName(it))
        }

        enableUnitTestCoverage = buildTypeProperties.enableUnitTestCoverage
        isDebuggable = buildTypeProperties.isDebuggable
        isMinifyEnabled = buildTypeProperties.isMinifyEnabled
        isShrinkResources = buildTypeProperties.isShrinkResources
    }
}
