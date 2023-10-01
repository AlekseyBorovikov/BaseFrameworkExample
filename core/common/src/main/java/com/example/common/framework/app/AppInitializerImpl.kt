package com.example.common.framework.app

/**
 * A class that initializes the necessary components when the application starts
 * */
class AppInitializerImpl(private vararg val initializers: AppInitializer) : AppInitializer {
    override fun init(application: CoreApplication) {
        initializers.forEach {
            it.init(application)
        }
    }
}

// Sample:
//
// Provide AppInitializerImpl:
// @Provides
// @Singleton
// fun provideAppInitializer(
//     timberInitializer: TimberInitializer,
//     firebaseInitializer: FirebaseInitializer,
// ): AppInitializer {
//     return AppInitializerImpl(timberInitializer, firebaseInitializer)
// }
//
// Use in application:
// @HiltAndroidApp
//class AndroidApp: CoreApplication() {
//
// @Inject
// lateinit var initializer: AppInitializer
//
// override fun onCreate() {
//     super.onCreate()
//     initializer.init(this)
// }
//
//}