package com.oliverspryn.gradle.extension

import org.gradle.api.artifacts.VersionCatalog

fun VersionCatalog.findPluginId(alias: String): String = findPlugin(alias).get().get().pluginId
fun VersionCatalog.findLibraryId(alias: String) = findLibrary(alias).get()
fun VersionCatalog.findVersionId(alias: String) = findVersion(alias).get().toString()
