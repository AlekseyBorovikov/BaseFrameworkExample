import extansions.implementation

object Versions {
    const val Room = "2.5.1"
    const val Security = "1.1.0-alpha06"
    const val Navigation = "2.5.3"

    object Gradle {
        const val plugin = "8.0.1"
        const val kotlin = "1.8.20"
    }

    object Kotlin {
        const val core = "1.10.1"
        const val lifecycle_runtime = "2.6.1"
        const val coroutines = "1.6.4"
    }

    object Async {
        const val work_runtime = "2.8.1"
        const val coroutine_android = "1.6.4"
        const val coroutine_core = "1.4.1"
        const val coroutines = "1.6.4"
    }

    object Support {
        const val appcompat = "1.6.1"
        const val material = "1.9.0"
        const val timber = "5.0.1"
    }

    object Testing {
        const val junit = "4.13.2"
    }

    object AndroidTesting {
        const val junit_ext = "1.1.5"
        const val espresso = "3.5.1"
    }

    object Network {
        const val retrofit = "2.9.0"
        const val okhttp = "5.0.0-alpha.5"

        const val kotlinX_serialization = "1.3.2"
        const val kotlinX_serialization_retrofit = "0.8.0"
    }

    object DaggerHilt {
        const val version = "2.44"
        const val compose = "1.0.0"
    }

    object GoogleServices {
        const val firebase = "23.2.0"
    }
}

object KotlinLib {
    const val Core = "androidx.core:core-ktx:${Versions.Kotlin.core}"
    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Kotlin.lifecycle_runtime}"
}

object SupportLib {
    const val Appcompat = "androidx.appcompat:appcompat:${Versions.Support.appcompat}"
    const val Timber = "com.jakewharton.timber:timber:${Versions.Support.timber}"
    const val Glide = "com.github.bumptech.glide:glide:4.15.1"
}

object DatabaseLib {
    const val Runtime = "androidx.room:room-runtime:${Versions.Room}"
    const val Compiler = "androidx.room:room-compiler:${Versions.Room}"
    const val Ktx = "androidx.room:room-ktx:${Versions.Room}"
}

object NetworkLib {
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
    const val RetrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.Network.retrofit}"
    const val Okhttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okhttp}"
    const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okhttp}"
    const val Gson = "com.google.code.gson:gson:2.10.1"
}

object DaggerHiltLib {
    const val Android = "com.google.dagger:hilt-android:${Versions.DaggerHilt.version}"
    const val Compiler = "com.google.dagger:hilt-android-compiler:${Versions.DaggerHilt.version}"
    const val Compose = "androidx.hilt:hilt-navigation-compose:${Versions.DaggerHilt.compose}"
}

object GoogleServicesLib {
    const val Firebase = "com.google.firebase:firebase-messaging-ktx:${Versions.GoogleServices.firebase}"
}

object SecurityLib {
    const val CryptoKtx = "androidx.security:security-crypto-ktx:${Versions.Security}"
}

object AsyncLib {
//    const val WorkRuntime = "androidx.work:work-runtime-ktx:${Versions.Async.work_runtime}"
    const val CoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Async.coroutine_android}"
    const val CoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Async.coroutine_core}"
//    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle.main}"
}

object NavigationLib {
    const val UI = "androidx.navigation:navigation-ui-ktx:${Versions.Navigation}"
    const val Fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation}"
}


object TestingLib {
    const val Junit = "junit:junit:${Versions.Testing.junit}"
    const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
}

object AndroidTestingLib {
    const val JunitExt = "androidx.test.ext:junit:${Versions.AndroidTesting.junit_ext}"
    const val EspressoCore = "androidx.test.espresso:espresso-core:${Versions.AndroidTesting.espresso}"
}