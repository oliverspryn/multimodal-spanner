package com.oliverspryn.android.multimodal.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.WindowLayoutInfo
import com.oliverspryn.android.multimodal.Classifier
import com.oliverspryn.android.multimodal.ui.navigation.MultimodalSpannerNavGraph
import com.oliverspryn.android.multimodal.ui.theme.MultimodalSpannerTheme
import kotlinx.coroutines.flow.StateFlow

@ExperimentalMaterial3Api
@Composable
fun MultimodalSpanner(
    devicePosture: StateFlow<WindowLayoutInfo>,
    windowDpSize: DpSize,
    classifier: Classifier = Classifier()
) {
    val devicePostureValue by devicePosture.collectAsState()
    val screenClassifier = classifier.createClassifier(
        devicePosture = devicePostureValue,
        windowDpSize = windowDpSize
    )

    MultimodalSpannerTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            MultimodalSpannerNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                screenClassifier = screenClassifier
            )
        }
    }
}