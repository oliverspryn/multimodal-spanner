import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.oliverspryn.gradle"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.dokka)
    compileOnly(libs.kotlin)
}

gradlePlugin {
    plugins {
        register("app") {
            id = "multimodal.app"
            implementationClass = "com.oliverspryn.gradle.plugin.ApplicationConventionPlugin"
        }

        register("centralrepository") {
            id = "multimodal.centralrepository"
            implementationClass = "com.oliverspryn.gradle.plugin.CentralRepositoryConventionPlugin"
        }

        register("compose") {
            id = "multimodal.compose"
            implementationClass = "com.oliverspryn.gradle.plugin.JetpackComposeConventionPlugin"
        }

        register("dokka") {
            id = "multimodal.dokka"
            implementationClass = "com.oliverspryn.gradle.plugin.DokkaConventionPlugin"
        }

        register("hilt") {
            id = "multimodal.hilt"
            implementationClass = "com.oliverspryn.gradle.plugin.HiltConventionPlugin"
        }

        register("library") {
            id = "multimodal.library"
            implementationClass = "com.oliverspryn.gradle.plugin.LibraryConventionPlugin"
        }
    }
}
