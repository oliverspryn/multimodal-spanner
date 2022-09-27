package com.oliverspryn.android.multimodal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.oliverspryn.android.multimodal.ui.MultimodalSpanner

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenCapture = ScreenCapture(this)
        val devicePosture = screenCapture.devicePosture()

        setContent {
            val windowDpSize = screenCapture.windowDpSize()

            MultimodalSpanner(
                devicePosture = devicePosture,
                windowDpSize = windowDpSize
            )
        }
    }
}
