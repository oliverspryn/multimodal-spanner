package com.oliverspryn.gradle.delegate

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class EnvironmentVariableDelegate(
    private val variableName: String? = null
) : ReadOnlyProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        val nameToUse = variableName ?: property.name

        return if (hasEnvVar(nameToUse)) {
            System.getenv(nameToUse) as String
        } else if (!hasEnvVar("CI")) {
            ""
        } else {
            throw IllegalStateException("Environment variable '$nameToUse' is not set.")
        }
    }

    private fun hasEnvVar(name: String): Boolean {
        return System.getenv(name) != null
    }
}

/**
 * Gets an environment variable in one of two ways:
 * ```kotlin
 * val JAVA_HOME: String by envVar() // Fetches the environment variable named "JAVA_HOME"
 * val javaHome: String by envVar("JAVA_HOME") // Fetches the environment variable named "JAVA_HOME"
 * ```
 *
 * @param variableName The name of the environment variable to fetch. If
 *     `null`, the name of the Kotlin variable will be used.
 * @return The value of the environment variable.
 * @throws IllegalStateException If the environment variable is not set
 *     only on a CI. If this is not a CI machine, an empty string is
 *     returned. This is to allow for local development without setting all
 *     environment variables. A CI is determined by the presence of the
 *     `CI` environment variable.
 */
fun envVar(variableName: String? = null) = EnvironmentVariableDelegate(variableName)
