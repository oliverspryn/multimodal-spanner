package com.oliverspryn.android.multimodal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.lifecycleScope
import com.oliverspryn.android.multimodal.ui.MultimodalSpanner

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenInfo = ScreenInfo(this)
        val devicePosture = screenInfo.captureDevicePosture()

        setContent {
            val windowDpSize = screenInfo.captureWindowDpSize()
            screenInfo.createClassifier(devicePosture, windowDpSize)

            MultimodalSpanner()
        }
    }
}
