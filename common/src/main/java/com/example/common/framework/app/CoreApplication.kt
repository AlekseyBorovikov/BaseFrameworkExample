package com.example.common.framework.app

import android.app.Application
import androidx.lifecycle.LifecycleEventObserver

/**
 * The [Application] instance in the application must inherit from this class
 * */
abstract class CoreApplication: Application(), LifecycleEventObserver