package com.oliverspryn.android.multimodal

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import com.oliverspryn.android.multimodal.model.Dimension
import com.oliverspryn.android.multimodal.model.ScreenClassifier
import com.oliverspryn.android.multimodal.model.WindowSizeClass

/**
 * Uses information about the device's foldable posture and the size
 * of the screen to determine what posture the screen is in and all
 * of the information that can be extracted about the size of the screen
 * and where its hinge is located.
 *
 * @constructor A default instance of this class without any initialization or other actions.
 */
class Classifier {

    private companion object {

        // Numbers per Google's official recommendations:
        // https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes
        private val COMPACT_HEIGHT_BREAKPOINT = 480.dp
        private val MEDIUM_HEIGHT_BREAKPOINT = 900.dp

        private val COMPACT_WIDTH_BREAKPOINT = 600.dp
        private val MEDIUM_WIDTH_BREAKPOINT = 840.dp
    }

    
    /**
     * Classifies the device as either fully opened or a foldable in one of
     * several possible postures, based on the information given to it
     * about the app's window.
     *
     * The window's size class uses breakpoints following Google's recommended
     * practices, as defined here:
     * [Window Size Classes](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes).
     *
     * @param foldingFeature A physical description of the screen's folded state, if a hinge is present.
     * @param windowDpSize A rectangle indicating the X and Y dimensions of the screen in DPs (density-independent pixels).
     * @return Data modeling the complete posture and size of the device's screen.
     */
    fun createClassifier(foldingFeature: FoldingFeature?, windowDpSize: DpSize) = when {
        foldingFeature == null -> createFullyOpenedDevice(windowDpSize)
        isBookMode(foldingFeature) -> createBookModeObject(foldingFeature)
        isTableTopMode(foldingFeature) -> createTableTopObject(foldingFeature)
        else -> createFullyOpenedDevice(windowDpSize)
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
            windowDpSize.height < COMPACT_HEIGHT_BREAKPOINT -> WindowSizeClass.COMPACT
            windowDpSize.height < MEDIUM_HEIGHT_BREAKPOINT -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }

        val windowWidthSizeClass = when {
            windowDpSize.width < COMPACT_WIDTH_BREAKPOINT -> WindowSizeClass.COMPACT
            windowDpSize.width < MEDIUM_WIDTH_BREAKPOINT -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
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
