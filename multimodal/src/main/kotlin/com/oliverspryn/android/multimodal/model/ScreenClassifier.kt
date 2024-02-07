package com.oliverspryn.android.multimodal.model

import android.graphics.Rect
import androidx.window.layout.FoldingFeature

/**
 * The parent type for all screen classification measurement properties,
 * regardless of whether the device is capable of folding its screen.
 */
sealed interface ScreenClassifier {

    /**
     * Indicates one of three possible outcomes for the screen:
     *
     * 1. The device is not a foldable. In this case, the device will never have
     * another classification type.
     * 2. The device is a foldable that is completely opened flat.
     * 3. The device is a foldable that is completely closed, shutting off the
     * use of the larger foldable screen and revealing the outward-facing screen.
     * An example of this in action would be a Samsung Galaxy Z Fold 4, when the
     * device is folded shut and the user is using the smaller screen which doesn't
     * fold whatsoever.
     *
     * @constructor A device without a folded screen with [Dimension]s along both the X and Y axes.
     * @param height A [Dimension] revealing the physical size of the screen in DPs and what class its size falls into.
     * @param width A [Dimension] revealing the physical size of the screen in DPs and what class its size falls into.
     * @property height A [Dimension] revealing the physical size of the screen in DPs and what class its size falls into.
     * @property width A [Dimension] revealing the physical size of the screen in DPs and what class its size falls into.
     *
     *  @see Dimension
     */
    data class FullyOpened(
        val height: Dimension,
        val width: Dimension
    ) : ScreenClassifier

    /**
     * Indicates that the device has a foldable screen, and that it is folded
     * into one of several possible postures:
     *
     *  - Book mode
     *  - Tabletop mode
     *
     * @property hingePosition Indicates the DP position through which the hinge cuts through the physical screen, whether it is horizontal a vertical.
     * @property hingeSeparationRatio Number between 0 and 1 indicating where the hinge lives. Often is 0.5 for most foldable devices with apps in full-screen mode, since most hinges run down the center of the screen. Apps in floating window mode will see this value change as the app is dragged around, moving its position relative to the device's hinge. Does not indicate the up/down or left/right direction of the hinge. See [hingePosition] for information on that.
     * @property isSeparating Indicates whether the hinge is visually diving the screen (like Microsoft Surface Duo) or seamless (like Samsung Galaxy Z Fold + Z Flip).
     * @property occlusionType Indicates if the screen is or is not obscured in any way by the hinge.
     */
    sealed interface HalfOpened : ScreenClassifier {

        val hingePosition: Rect
        val hingeSeparationRatio: Float
        val isSeparating: Boolean
        val occlusionType: FoldingFeature.OcclusionType

        /**
         * Indicates the device is in book mode, where the hinge runs from top to
         * bottom and the device is in a folded position, like the natural use of a
         * book.
         *
         * @param hingePosition Indicates the DP position through which the hinge
         *     cuts through the physical screen, whether it is horizontal a
         *     vertical.
         * @param hingeSeparationRatio Number between 0 and 1 indicating where the
         *     hinge lives. Often is 0.5 for most foldable devices with apps in
         *     full-screen mode, since most hinges run down the center of the
         *     screen. Apps in floating window mode will see this value change as
         *     the app is dragged around, moving its position relative to the
         *     device's hinge. Does not indicate the up/down or left/right
         *     direction of the hinge. See [hingePosition] for information on that.
         * @param isSeparating Indicates whether the hinge is visually diving the
         *     screen (like Microsoft Surface Duo) or seamless (like Samsung Galaxy
         *     Z Fold + Z Flip).
         * @param occlusionType Indicates if the screen is or is not obscured in
         *     any way by the hinge.
         * @constructor All available information about a foldable screen in book
         *     mode.
         */
        data class BookMode(
            override val hingePosition: Rect,
            override val hingeSeparationRatio: Float,
            override val isSeparating: Boolean,
            override val occlusionType: FoldingFeature.OcclusionType
        ) : HalfOpened

        /**
         * Indicates the device is in tabletop mode, where the hinge runs from left
         * to right and device is in a folded position, much like how a laptop
         * would sit on a table.
         *
         * @param hingePosition Indicates the DP position through which the hinge
         *     cuts through the physical screen, whether it is horizontal a
         *     vertical.
         * @param hingeSeparationRatio Number between 0 and 1 indicating where the
         *     hinge lives. Often is 0.5 for most foldable devices with apps in
         *     full-screen mode, since most hinges run down the center of the
         *     screen. Apps in floating window mode will see this value change as
         *     the app is dragged around, moving its position relative to the
         *     device's hinge. Does not indicate the up/down or left/right
         *     direction of the hinge. See [hingePosition] for information on that.
         * @param isSeparating Indicates whether the hinge is visually diving the
         *     screen (like Microsoft Surface Duo) or seamless (like Samsung Galaxy
         *     Z Fold + Z Flip).
         * @param occlusionType Indicates if the screen is or is not obscured in
         *     any way by the hinge.
         * @constructor All available information about a foldable screen in
         *     tabletop mode.
         */
        data class TableTopMode(
            override val hingePosition: Rect,
            override val hingeSeparationRatio: Float,
            override val isSeparating: Boolean,
            override val occlusionType: FoldingFeature.OcclusionType
        ) : HalfOpened
    }
}
