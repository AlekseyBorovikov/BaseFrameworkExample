package com.example.common.framework.app

/**
 * Interface for implementing component initialization at application startup
 * */
interface AppInitializer {
    fun init(application: CoreApplication)
}