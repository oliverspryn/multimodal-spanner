package com.oliverspryn.android.multimodal.model

/**
 * Classifies a [ScreenClassifier.FullyOpened] screen into one of three
 * size classes: Compact, Medium, or Expanded. Does not indicate a directional
 * axes, and is used twice by the [com.oliverspryn.android.multimodal.Classifier]
 * to indicate the size of the screen along both in the X and Y axes.
 *
 * These breakpoints follow Google's recommended practices, as defined here:
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes
 *
 * @see com.oliverspryn.android.multimodal.Classifier
 * @see ScreenClassifier
 */
enum class WindowSizeClass {
    COMPACT,
    MEDIUM,
    EXPANDED
}
