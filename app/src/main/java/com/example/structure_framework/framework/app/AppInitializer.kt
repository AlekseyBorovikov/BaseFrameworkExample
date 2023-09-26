package com.example.structure_framework.framework.app

/**
 * Interface for implementing component initialization at application startup
 * */
interface AppInitializer {
    fun init(application: CoreApplication)
}