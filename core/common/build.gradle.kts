import extansions.addDaggerHiltDependencies
import extansions.addKotlinDependencies
import extansions.addNetworkDependencies
import extansions.addSecurityDependencies
import extansions.addSupportDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.structure_framework.core.common"
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
        freeCompilerArgs = Configs.FreeCompilerArgs
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    addKotlinDependencies()
    addNetworkDependencies()
    addSupportDependencies()
    addSecurityDependencies()
    addDaggerHiltDependencies()
    implementation(LifecycleLib.ViewModel)
}