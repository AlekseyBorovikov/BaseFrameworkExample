package com.example.app

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.common.framework.app.AppInitializer
import com.example.common.framework.app.CoreApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class RickAndMortyApplication : CoreApplication() {

    @Inject
    lateinit var initializer: AppInitializer
    override fun onCreate() {
        super.onCreate()
        initializer.init(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) = Unit
}