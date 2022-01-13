package com.oliverspryn.android.multimodal.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.oliverspryn.android.multimodal.ui.navigation.MultimodalSpannerNavGraph
import com.oliverspryn.android.multimodal.ui.theme.MultimodalSpannerTheme

@ExperimentalMaterial3Api
@Composable
fun MultimodalSpanner() {
    MultimodalSpannerTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            MultimodalSpannerNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
