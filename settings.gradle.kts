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

include (":core:network")
include (":core:data")
include (":core:datastore")
include (":core:database")
include(":core:common")
include(":core:domain")
include(":core:design")
include(":core:testing")

include(":feature:contacts")

include (":sync:work")
include (":sync:sync-test")
