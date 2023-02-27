import org.jetbrains.dokka.gradle.DokkaTaskPartial

plugins {
    `maven-publish`
    signing

    id("com.android.library")
    id("org.jetbrains.dokka") version Versions.DOKKA
    kotlin("android")
    kotlin("kapt")
}

apply(plugin = "org.jetbrains.dokka")

android {
    compileSdk = Versions.COMPILE_SDK
    namespace = Config.APPLICATION_ID

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = Versions.JVM
        targetCompatibility = Versions.JVM
    }

    kotlinOptions {
        jvmTarget = Versions.JVM_STRING
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }

    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

dependencies {
    api(platform(project(":constraints")))
    kapt(platform(project(":constraints")))

    /////////////////////////////////////////////////////////////////////

    // region Application

    implementation(Libraries.ACTIVITY_COMPOSE)
    implementation(Libraries.COMPOSE_UI_UNIT)
    implementation(Libraries.WINDOW_MANAGER)

    // endregion
}

/////////////////////////////////////////////////////////////////////

group = CentralRepository.Artifact.GROUP_ID
version = CentralRepository.Artifact.VERSION

publishing {
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

signing {
    val GPG_SIGNING_KEY: String? by project
    val GPG_SIGNING_KEY_PASSWORD: String? by project
    useInMemoryPgpKeys(GPG_SIGNING_KEY, GPG_SIGNING_KEY_PASSWORD)
    sign(publishing.publications[CentralRepository.LIBRARY_NAME])
}

tasks.withType<DokkaTaskPartial>().configureEach {
    moduleName.set(Config.PROJECT_NAME)
    moduleVersion.set(CentralRepository.Artifact.VERSION)
}
