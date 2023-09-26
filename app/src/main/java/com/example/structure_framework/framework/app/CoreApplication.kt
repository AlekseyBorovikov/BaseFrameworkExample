package com.example.structure_framework.framework.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * The [Application] instance in the application must inherit from this class
 * */
abstract class CoreApplication: Application(), LifecycleEventObserver