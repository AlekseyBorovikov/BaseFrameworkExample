package com.example.structure_framework.framework.app

import timber.log.Timber

/**
 * [Timber] initialization at application startup
 * */
class TimberInitializer(private val isDev: Boolean): AppInitializer {
    override fun init(application: CoreApplication) {
        if (isDev) {
            Timber.plant(Timber.DebugTree())
        }
    }

}