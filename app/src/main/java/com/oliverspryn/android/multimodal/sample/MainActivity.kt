package com.oliverspryn.android.multimodal.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.oliverspryn.android.multimodal.ScreenCapture
import com.oliverspryn.android.multimodal.sample.ui.MultimodalSpanner
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var screenCapture: ScreenCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenCapture.init(this)
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
