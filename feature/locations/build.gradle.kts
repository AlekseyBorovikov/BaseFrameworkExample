import extansions.CORE_COMMON_MODULE
import extansions.CORE_DOMAIN_MODULE
import extansions.CORE_MODEL_MODULE
import extansions.CORE_RESOURCES_MODULE
import extansions.addDaggerHiltDependencies
import extansions.addKotlinDependencies
import extansions.addNavigationDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.locations"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    addKotlinDependencies()
    addDaggerHiltDependencies()
    addNavigationDependencies()

    CORE_COMMON_MODULE
    CORE_DOMAIN_MODULE
    CORE_MODEL_MODULE
    CORE_RESOURCES_MODULE
}