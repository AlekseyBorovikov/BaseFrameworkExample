package extansions

import NetworkLib
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

/**
 * Adds a dependency to the `releaseImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.releaseImplementation(dependencyNotation: Any): Dependency? =
    add("releaseImplementation", dependencyNotation)

/**
 * Adds a dependency to the `debugImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)


/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the `ksp` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.ksp(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)

fun DependencyHandler.addKotlinDependencies() {
    implementation(KotlinLib.Core)
    implementation(KotlinLib.LifecycleRuntime)
}

fun DependencyHandler.addDaggerHiltDependencies() {
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
    implementation(DaggerHiltLib.Compose)
}

fun DependencyHandler.addSupportDependencies() {
    implementation(SupportLib.Timber)
    implementation(SupportLib.Appcompat)
}

fun DependencyHandler.addGoogleServicesDependencies() {
    implementation(GoogleServicesLib.Firebase)
}

fun DependencyHandler.addNetworkDependencies() {
    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitGson)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
    implementation(NetworkLib.Gson)
}

fun DependencyHandler.addDatabaseDependencies() {
    implementation(DatabaseLib.Runtime)
    kapt(DatabaseLib.Compiler)
    implementation(DatabaseLib.Ktx)
}

fun DependencyHandler.addSecurityDependencies() {
    implementation(SecurityLib.CryptoKtx)
}

fun DependencyHandler.addTestDependencies() {
    testImplementation(TestingLib.Junit)
    androidTestImplementation(AndroidTestingLib.JunitExt)
    androidTestImplementation(AndroidTestingLib.EspressoCore)
}

val DependencyHandler.CORE_DATA_MODULE
    get() = implementation(project(mapOf("path" to ":core:data")))

val DependencyHandler.CORE_COMMON_MODULE
    get() = implementation(project(mapOf("path" to ":core:common")))

val DependencyHandler.CORE_DOMAIN_MODULE
    get() = implementation(project(mapOf("path" to ":core:domain")))

val DependencyHandler.CORE_LOCALE_MODULE
    get() = implementation(project(mapOf("path" to ":core:locale")))

val DependencyHandler.CORE_REMOTE_MODULE
    get() = implementation(project(mapOf("path" to ":core:remote")))

val DependencyHandler.FEATURE_CHARACTERS_MODULE
    get() = implementation(project(mapOf("path" to ":feature:characters")))

val DependencyHandler.FEATURE_EPISODES_MODULE
    get() = implementation(project(mapOf("path" to ":feature:episodes")))

val DependencyHandler.FEATURE_LOCATIONS_MODULE
    get() = implementation(project(mapOf("path" to ":feature:locations")))