package com.oliverspryn.android.multimodal

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowMetricsCalculator
import com.oliverspryn.android.multimodal.exceptions.MissingActivityException
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Captures raw, unclassified data about the screen's dimensions and
 * information about the device's hinge, assuming it is present.
 *
 * @constructor A default instance of this class without any initialization
 *     or other actions.
 */
class ScreenCapture {

    private var activity: ComponentActivity? = null

    /**
     * For use outside composable functions.
     *
     * Initializes the capture class with a copy of the activity it will be
     * using to measure the screen against.
     *
     * This function is broken out separately from the constructor so that
     * you can use the [ScreenCapture] class itself inside of DI framework,
     * like Hilt, and later set up the capturing capabilities in the
     * `onCreate()` function of a [ComponentActivity].
     *
     * @param activity An activity which will be used to measure the screen.
     */
    fun init(activity: ComponentActivity) {
        this.activity = activity
    }

    /**
     * For use outside composable functions. [init] must be called first!
     *
     * Captures the raw, unclassified data from the video about the state of a
     * foldable hinge, if it is present. All updates are pushed with real time
     * updates via a Kotlin [StateFlow] object.
     *
     * @return Raw data about the device's foldable hinge, pushed in real time
     *     via a Kotlin [StateFlow] object.
     * @throws MissingActivityException If the [init] function isn't called
     *     before this one.
     */
    fun foldingFeature(): StateFlow<FoldingFeature?> {
        val requiredActivity = activity ?: throw MissingActivityException

        return WindowInfoTracker
            .getOrCreate(requiredActivity)
            .windowLayoutInfo(requiredActivity)
            .map { windowInfo ->
                windowInfo.displayFeatures.find {
                    it is FoldingFeature
                } as? FoldingFeature
            }
            .stateIn(
                scope = requiredActivity.lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = null
            )
    }

    /**
     * For use in composable functions only. [init] must be called first!
     *
     * Returns the raw, unprocessed data showing the X and Y dimensions of the
     * window, measured in DPs (density-independent pixels). These updates are
     * properly adjusted when the app is in split mode or windowed mode. It
     * will then measure the size of the app's window, instead of the entire
     * screen.
     *
     * @return Current size of the app window, taking into account full screen,
     *     split screen, and windowed apps.
     * @throws MissingActivityException If the [init] function isn't called
     *     before this one.
     */
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
