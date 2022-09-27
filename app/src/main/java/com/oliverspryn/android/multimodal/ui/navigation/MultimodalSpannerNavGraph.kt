package com.oliverspryn.android.multimodal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oliverspryn.android.multimodal.model.ScreenClassifier
import com.oliverspryn.android.multimodal.ui.adaptivelayouts.AdaptiveLayoutsRoute
import com.oliverspryn.android.multimodal.ui.adaptivelayouts.AdaptiveLayoutsViewModel
import com.oliverspryn.android.multimodal.ui.home.HomeRoute
import com.oliverspryn.android.multimodal.ui.screeninfo.ScreenInfoRoute

@Composable
fun MultimodalSpannerNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    screenClassifier: ScreenClassifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Home,
        modifier = modifier
    ) {
        composable(Destinations.Home) {
            HomeRoute(
                onViewScreenInfoButtonTap = { navController.navigate(Destinations.ScreenInfo) },
                onViewAdaptiveLayoutsButtonTap = { navController.navigate(Destinations.AdaptiveLayouts) }
            )
        }

        composable(Destinations.ScreenInfo) {
            ScreenInfoRoute(screenClassifier)
        }

        composable(Destinations.AdaptiveLayouts) {
            val viewModel: AdaptiveLayoutsViewModel = viewModel()

            AdaptiveLayoutsRoute(
                adaptiveLayoutsViewModel = viewModel,
                screenClassifier = screenClassifier
            )
        }
    }
}
