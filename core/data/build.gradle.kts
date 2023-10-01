import extansions.CORE_COMMON_MODULE
import extansions.CORE_MODEL_MODULE
import extansions.CORE_REMOTE_MODULE
import extansions.CORE_LOCALE_MODULE
import extansions.addDaggerHiltDependencies
import extansions.addKotlinDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.data"
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

    addKotlinDependencies()
    addDaggerHiltDependencies()

    CORE_COMMON_MODULE
    CORE_LOCALE_MODULE
    CORE_MODEL_MODULE
    CORE_REMOTE_MODULE
}