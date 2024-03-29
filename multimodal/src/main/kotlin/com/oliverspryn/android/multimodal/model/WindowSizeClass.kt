package com.oliverspryn.android.multimodal.model

import com.oliverspryn.android.multimodal.Classifier

/**
 * Classifies a [ScreenClassifier.FullyOpened] screen into one of three
 * size classes: Compact, Medium, or Expanded. Does not indicate a
 * directional axis, and is used twice by the [Classifier] to individually
 * represent the size of the screen along both the X and Y axes.
 *
 * These breakpoints follow Google's recommended practices, as defined
 * here:
 * [Window Size Classes](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes).
 *
 * @see Classifier
 * @see ScreenClassifier
 */
enum class WindowSizeClass {
    COMPACT,
    MEDIUM,
    EXPANDED
}
