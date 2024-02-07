package com.oliverspryn.android.multimodal.model

import androidx.compose.ui.unit.Dp

/**
 * Models the dimension properties of the device's screen along one axis,
 * such as either the X axis or Y axis. Two [Dimension] classes together
 * are used to define the complete dimensions of a screen along both axes.
 *
 * @param dp Size of the screen in DPs (density-independent pixels).
 * @param sizeClass Classification of the screen size based on pre-defined
 *     breakpoints defined by Google, as followed in [ScreenClassifier].
 * @constructor A measurement and classification of the screen size.
 * @property dp Size of the screen in DPs (density-independent pixels).
 * @property sizeClass Classification of the screen size based on
 *     pre-defined breakpoints defined by Google, as followed in
 *     [ScreenClassifier].
 * @see ScreenClassifier
 * @see WindowSizeClass
 */
data class Dimension(
    val dp: Dp,
    val sizeClass: WindowSizeClass
)
