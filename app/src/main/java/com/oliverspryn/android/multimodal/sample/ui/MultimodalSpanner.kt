package com.oliverspryn.android.multimodal.sample.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.FoldingFeature
import com.oliverspryn.android.multimodal.Classifier
import com.oliverspryn.android.multimodal.sample.ui.navigation.MultimodalSpannerNavGraph
import com.oliverspryn.android.multimodal.sample.ui.theme.MultimodalSpannerTheme
import kotlinx.coroutines.flow.StateFlow

@ExperimentalMaterial3Api
@Composable
fun MultimodalSpanner(
    foldingFeature: StateFlow<FoldingFeature?>,
    windowDpSize: DpSize,
    classifier: Classifier = Classifier()
) {
    val foldingFeatureValue by foldingFeature.collectAsState()
    val screenClassifier = classifier.createClassifier(
        foldingFeature = foldingFeatureValue,
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
