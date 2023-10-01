import extansions.CORE_MODEL_MODULE
import extansions.addDaggerHiltDependencies
import extansions.addDatabaseDependencies
import extansions.addKotlinDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.locale"
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
    addDatabaseDependencies()

    addDaggerHiltDependencies()

    CORE_MODEL_MODULE
}