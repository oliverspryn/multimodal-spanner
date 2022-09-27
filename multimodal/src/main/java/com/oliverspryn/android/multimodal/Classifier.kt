package com.oliverspryn.android.multimodal

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowLayoutInfo
import com.oliverspryn.android.multimodal.model.Dimension
import com.oliverspryn.android.multimodal.model.ScreenClassifier
import com.oliverspryn.android.multimodal.model.WindowSizeClass

class Classifier {

    @Composable
    fun createClassifier(devicePosture: WindowLayoutInfo, windowDpSize: DpSize): ScreenClassifier {
        val foldingFeature = devicePosture.displayFeatures.find {
            it is FoldingFeature
        } as? FoldingFeature

        return when {
            foldingFeature == null -> createFullyOpenedDevice(windowDpSize)
            isBookMode(foldingFeature) -> createBookModeObject(foldingFeature)
            isTableTopMode(foldingFeature) -> createTableTopObject(foldingFeature)
            else -> createFullyOpenedDevice(windowDpSize)
        }
    }

    private fun createBookModeObject(foldingFeature: FoldingFeature): ScreenClassifier.HalfOpened.BookMode {
        val screenWidth = foldingFeature.bounds.left + foldingFeature.bounds.right
        val ratio = foldingFeature.bounds.left.toFloat() / screenWidth.toFloat()

        return ScreenClassifier.HalfOpened.BookMode(
            hingePosition = foldingFeature.bounds,
            hingeSeparationRatio = ratio,
            isSeparating = foldingFeature.isSeparating,
            occlusionType = foldingFeature.occlusionType
        )
    }

    private fun createFullyOpenedDevice(windowDpSize: DpSize): ScreenClassifier.FullyOpened {
        val windowHeightSizeClass = when {
            windowDpSize.height < 480.dp -> WindowSizeClass.Compact
            windowDpSize.height < 900.dp -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }

        val windowWidthSizeClass = when {
            windowDpSize.width < 600.dp -> WindowSizeClass.Compact
            windowDpSize.width < 840.dp -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }

        return ScreenClassifier.FullyOpened(
            height = Dimension(
                dp = windowDpSize.height,
                sizeClass = windowHeightSizeClass
            ),
            width = Dimension(
                dp = windowDpSize.width,
                sizeClass = windowWidthSizeClass
            )
        )
    }

    private fun createTableTopObject(foldingFeature: FoldingFeature): ScreenClassifier.HalfOpened.TableTopMode {
        val screenHeight = foldingFeature.bounds.top + foldingFeature.bounds.bottom
        val ratio = foldingFeature.bounds.top.toFloat() / screenHeight.toFloat()

        return ScreenClassifier.HalfOpened.TableTopMode(
            hingePosition = foldingFeature.bounds,
            hingeSeparationRatio = ratio,
            isSeparating = foldingFeature.isSeparating,
            occlusionType = foldingFeature.occlusionType
        )
    }

    private fun isBookMode(foldingFeature: FoldingFeature) =
        foldingFeature.state == FoldingFeature.State.HALF_OPENED && foldingFeature.orientation == FoldingFeature.Orientation.VERTICAL

    private fun isTableTopMode(foldingFeature: FoldingFeature) =
        foldingFeature.state == FoldingFeature.State.HALF_OPENED && foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL
}
