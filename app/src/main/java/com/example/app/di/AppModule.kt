package com.example.app.di

import com.example.common.framework.app.AppInitializer
import com.example.common.framework.app.AppInitializerImpl
import com.example.common.framework.app.TimberInitializer
import com.example.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTimberInitializer() = TimberInitializer(BuildConfig.DEBUG)

    @Provides
    @Singleton
    fun provideAppInitializer(
        timberInitializer: TimberInitializer
    ): AppInitializer {
        return AppInitializerImpl(timberInitializer)
    }

}