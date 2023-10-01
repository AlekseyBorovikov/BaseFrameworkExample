import extansions.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.app"
    compileSdk = Configs.CompileSdk

    defaultConfig {
        applicationId = Configs.Id
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        versionCode = Configs.VersionCode
        versionName = Configs.VersionName

        testInstrumentationRunner = Configs.AndroidJunitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
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
    addSupportDependencies()
    addNavigationDependencies()
    addDaggerHiltDependencies()
    implementation(UILib.Material)

    CORE_COMMON_MODULE
    FEATURE_CHARACTERS_MODULE
    FEATURE_EPISODES_MODULE
    FEATURE_LOCATIONS_MODULE
    CORE_RESOURCES_MODULE
}