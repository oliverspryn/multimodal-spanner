package com.oliverspryn.gradle.delegate

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import org.gradle.api.Project

class GradlePropertyDelegate<T>(
    private val project: Project,
    private val variableName: String? = null
) : ReadOnlyProperty<Any?, T> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val nameToUse = variableName ?: property.name
        val outcome = project.findProperty(nameToUse) as T

        if (outcome == null) {
            throw IllegalStateException("Gradle property variable '$nameToUse' is not set.")
        } else {
            return outcome
        }
    }
}

/**
 * Gets a Gradle property in one of two ways:
 * ```kotlin
 * val propertyName: String by propertyValue() // Fetches the value of "propertyName" in gradle.properties
 * val property: String by propertyValue("propertyName") // Fetches the value of "propertyName" in gradle.properties
 * ```
 *
 * @param propertyName The name of the property to fetch. If `null`, the
 *     name of the Kotlin variable will be used.
 * @return The value of the Gradle property.
 * @throws IllegalStateException If the Gradle property is not set.
 */
fun <T> Project.propertyValue(propertyName: String? = null) =
    GradlePropertyDelegate<T>(this, propertyName)
