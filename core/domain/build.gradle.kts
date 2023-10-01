import extansions.CORE_COMMON_MODULE
import extansions.CORE_DATA_MODULE
import extansions.CORE_MODEL_MODULE
import extansions.addDaggerHiltDependencies
import extansions.addKotlinDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    addKotlinDependencies()
    addDaggerHiltDependencies()

    CORE_MODEL_MODULE
    CORE_DATA_MODULE
    CORE_COMMON_MODULE
}