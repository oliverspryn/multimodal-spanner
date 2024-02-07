package com.oliverspryn.android.multimodal.exceptions

import androidx.activity.ComponentActivity
import com.oliverspryn.android.multimodal.ScreenCapture

/**
 * Indicates that the framework was not initialized properly by providing a
 * [ComponentActivity] (or one of its child classes) to the initialization
 * function prior to usage.
 *
 * @see ScreenCapture
 * @see ScreenCapture.init
 */
object MissingActivityException :
    Throwable("No Activity is available to the framework. Did you call ScreenCapture.init(activity) before making any other calls?")
