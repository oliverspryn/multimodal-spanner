package com.oliverspryn.android.multimodal.di

import com.oliverspryn.android.multimodal.ScreenCapture
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ScreenCaptureModule {

    @Provides
    fun provideScreenCapture() = ScreenCapture()
}
