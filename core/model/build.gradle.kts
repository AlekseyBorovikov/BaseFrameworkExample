plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Configs.javaVersion
    targetCompatibility = Configs.javaVersion
}