plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.resources"
    compileSdk = Configs.CompileSdk

    defaultConfig {
        minSdk = Configs.MinSdk

        testInstrumentationRunner = Configs.AndroidJunitRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = Configs.FreeCompilerArgs
    }
}

dependencies {
    implementation(UILib.Material)
}