package com.oliverspryn.android.multimodal

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import androidx.window.layout.WindowMetricsCalculator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

open class ScreenCapture(
    private val activity: ComponentActivity
) {

    fun devicePosture() = WindowInfoTracker
        .getOrCreate(activity)
        .windowLayoutInfo(activity)
        .stateIn(
            scope = activity.lifecycleScope,
            started = SharingStarted.Eagerly,
            initialValue = WindowLayoutInfo(emptyList())
        )

    @Composable
    fun windowDpSize(): DpSize {
        val configuration = LocalConfiguration.current

        val windowMetrics = remember(configuration) {
            WindowMetricsCalculator
                .getOrCreate()
                .computeCurrentWindowMetrics(activity)
        }

        val windowDpSize = with(LocalDensity.current) {
            windowMetrics
                .bounds
                .toComposeRect()
                .size
                .toDpSize()
        }

        return windowDpSize
    }
}
