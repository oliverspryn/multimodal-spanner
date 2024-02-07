package com.oliverspryn.gradle.plugin

import com.android.build.api.dsl.LibraryExtension
import com.oliverspryn.gradle.BuildConfig
import com.oliverspryn.gradle.CentralRepository
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.authentication.http.BasicAuthentication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.credentials
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.repositories
import org.gradle.plugins.signing.SigningExtension

class PublishConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("maven-publish")
                apply("signing")
            }

            extensions.configure<LibraryExtension> {
                publishing {
                    singleVariant(BuildConfig.BuildType.Release.name) {
                        withJavadocJar()
                        withSourcesJar()
                    }
                }
            }

            group = CentralRepository.Artifact.GROUP_ID
            version = CentralRepository.Artifact.VERSION

            extensions.configure<PublishingExtension> {
                publications {
                    create<MavenPublication>(CentralRepository.LIBRARY_NAME) {
                        groupId = CentralRepository.Artifact.GROUP_ID
                        artifactId = CentralRepository.Artifact.ID
                        version = CentralRepository.Artifact.VERSION

                        afterEvaluate {
                            from(components["release"])
                        }

                        pom {
                            name.set(CentralRepository.Project.NAME)
                            description.set(CentralRepository.Project.DESCRIPTION)
                            url.set(CentralRepository.Project.URL)

                            developers {
                                developer {
                                    id.set(CentralRepository.Developer.ID)
                                    name.set(CentralRepository.Developer.NAME)
                                    url.set(CentralRepository.Developer.URL)
                                }
                            }

                            issueManagement {
                                url.set("https://${CentralRepository.SCM.URL}/issues")
                            }

                            licenses {
                                license {
                                    name.set(CentralRepository.License.NAME)
                                    name.set(CentralRepository.License.URL)
                                }
                            }

                            scm {
                                connection.set("scm:git:git://${CentralRepository.SCM.URL}.git")
                                developerConnection.set("scm:git:ssh://${CentralRepository.SCM.URL}.git")
                                url.set("https://${CentralRepository.SCM.URL}")
                            }
                        }
                    }
                }

                repositories {
                    maven {
                        val releaseUri = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                        val snapshotUri = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

                        name = "central"
                        url = if (CentralRepository.Artifact.VERSION.endsWith("SNAPSHOT")) snapshotUri else releaseUri

                        credentials(PasswordCredentials::class)

                        authentication {
                            create<BasicAuthentication>("basic")
                        }
                    }
                }
            }

            extensions.configure<SigningExtension> {
                @Suppress("ktlint:standard:property-naming")
                val GPG_SIGNING_KEY: String? by project
                val GPG_SIGNING_KEY_PASSWORD: String? by project

                useInMemoryPgpKeys(GPG_SIGNING_KEY, GPG_SIGNING_KEY_PASSWORD)
                sign(extensions.getByType<PublishingExtension>().publications[CentralRepository.LIBRARY_NAME])
            }
        }
    }
}
