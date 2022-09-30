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
import com.oliverspryn.android.multimodal.exceptions.MissingActivityException
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ScreenCapture {

    private var activity: ComponentActivity? = null

    fun init(activity: ComponentActivity) {
        this.activity = activity
    }

    fun devicePosture(): StateFlow<WindowLayoutInfo> {
        val requiredActivity = activity ?: throw MissingActivityException

        return WindowInfoTracker
            .getOrCreate(requiredActivity)
            .windowLayoutInfo(requiredActivity)
            .stateIn(
                scope = requiredActivity.lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = WindowLayoutInfo(emptyList())
            )
    }

    @Composable
    fun windowDpSize(): DpSize {
        val requiredActivity = activity ?: throw MissingActivityException
        val configuration = LocalConfiguration.current

        val windowMetrics = remember(configuration) {
            WindowMetricsCalculator
                .getOrCreate()
                .computeCurrentWindowMetrics(requiredActivity)
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
