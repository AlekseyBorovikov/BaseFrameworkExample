package com.example.structure_framework.framework.app

import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber

/**
 * Firebase initialization at application startup
 * */
class FirebaseInitializer: AppInitializer {
    override fun init(application: CoreApplication) {
        FirebaseApp.initializeApp(application)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.w("Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Timber.d("new token $token")
        }
    }

}