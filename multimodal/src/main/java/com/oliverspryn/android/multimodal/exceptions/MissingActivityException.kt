package com.oliverspryn.android.multimodal.exceptions

/**
 * Indicates that the framework was not initialized properly by providing
 * a [androidx.activity.ComponentActivity] (or one of it's child classes)
 * to the initialization function prior to usage.
 *
 * @see com.oliverspryn.android.multimodal.ScreenCapture
 * @see com.oliverspryn.android.multimodal.ScreenCapture.init
 */
object MissingActivityException :
    Throwable("No activity is available to the framework. Did you call ScreenCapture.init(activity) before making any other calls?")
