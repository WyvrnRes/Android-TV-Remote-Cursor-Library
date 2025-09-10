pluginManagement {
    repositories {
        google()          // <- necesario para Android Gradle Plugin
        mavenCentral()    // <- necesario para Kotlin y otras dependencias
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

rootProject.name = "TVBrowser"
include(":app")
