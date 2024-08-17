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

rootProject.name = "desafio-android"

include (":app")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:design")
include(":feature:contacts")