package com.oliverspryn.android.multimodal.exceptions

object MissingActivityException :
    Throwable("No activity is available to the framework. Did you call ScreenCapture.init(activity) before making any other calls?")
