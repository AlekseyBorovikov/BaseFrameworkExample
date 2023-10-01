pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "rick_and_morty_example"
include(":app")
include(":core:common")
include(":core:data")
include(":core:locale")
include(":core:remote")
include(":core:domain")
include(":feature:characters")
include(":feature:episodes")
include(":feature:locations")
