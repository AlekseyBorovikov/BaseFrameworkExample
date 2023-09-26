package com.example.structure_framework.tools

import android.os.Build

/**
 * Helper function that creates [Lazy] instance using [lazy] with [LazyThreadSafetyMode#NONE].
 *
 * @param T Type of lazy value
 *
 * @param initializer Function to initialize lazy value
 *
 * @return [Lazy] instance without usage of synchronization locks
 */
fun <T> unsafeLazy(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

/**
 * Oreo Version Checker
 *
 * The method does not guarantee that the version of the system is Oreo,
 * it guarantees that the version of the system on which the program is running is Oreo or higher.
 * */
inline fun <R> versionOreo(block: () -> R) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) block.invoke()
}