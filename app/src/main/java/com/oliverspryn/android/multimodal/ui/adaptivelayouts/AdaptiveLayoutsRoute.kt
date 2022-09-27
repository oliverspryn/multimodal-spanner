package com.oliverspryn.android.multimodal.ui.adaptivelayouts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.oliverspryn.android.multimodal.model.ScreenClassifier
import com.oliverspryn.android.multimodal.model.WindowSizeClass

@Composable
fun AdaptiveLayoutsRoute(
    adaptiveLayoutsViewModel: AdaptiveLayoutsViewModel,
    screenClassifier: ScreenClassifier
) {
    val uiState by adaptiveLayoutsViewModel.uiState.collectAsState()

    AdaptiveLayoutsRoute(
        screenClassifier = screenClassifier,
        uiState = uiState
    )
}

@Composable
private fun AdaptiveLayoutsRoute(
    screenClassifier: ScreenClassifier,
    uiState: AdaptiveLayoutsUiState,
) {
    var adaptiveLayoutsScreenType by rememberSaveable { mutableStateOf(AdaptiveLayoutsScreenType.ListOnly) }
    adaptiveLayoutsScreenType =
        screenClassifier.toAdaptiveLayoutsScreenType(numberSelected = uiState.numberSelected)

    val name = when (adaptiveLayoutsScreenType) {
        AdaptiveLayoutsScreenType.ListOnly -> "List"
        AdaptiveLayoutsScreenType.DetailOnly -> "Detail"
        AdaptiveLayoutsScreenType.ListOneThirdAndDetailTwoThirds -> "1/3 and 2/3"
        AdaptiveLayoutsScreenType.ListHalfAndDetailHalf -> "1/2 and 1/2"
        AdaptiveLayoutsScreenType.ListDetailStacked -> "Stacked"
    }

    Text(text = name)
}

enum class AdaptiveLayoutsScreenType {
    ListOnly,
    DetailOnly,
    ListOneThirdAndDetailTwoThirds,
    ListHalfAndDetailHalf,
    ListDetailStacked
}

@Composable
private fun ScreenClassifier.toAdaptiveLayoutsScreenType(numberSelected: Boolean) =
    if (this is ScreenClassifier.FullyOpened && width.sizeClass == WindowSizeClass.Expanded) {
        AdaptiveLayoutsScreenType.ListOneThirdAndDetailTwoThirds
    } else if (this is ScreenClassifier.FullyOpened && !numberSelected) {
        AdaptiveLayoutsScreenType.ListOnly
    } else if (this is ScreenClassifier.FullyOpened && numberSelected) {
        AdaptiveLayoutsScreenType.DetailOnly
    } else if (this is ScreenClassifier.HalfOpened.BookMode) {
        AdaptiveLayoutsScreenType.ListHalfAndDetailHalf
    } else if (this is ScreenClassifier.HalfOpened.TableTopMode) {
        AdaptiveLayoutsScreenType.ListDetailStacked
    } else {
        AdaptiveLayoutsScreenType.ListOnly
    }
