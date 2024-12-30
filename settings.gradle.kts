pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "IClickIPay"
include(":app")
include(":data")
include(":domain")
include(":feature_pet")
include(":feature_learn")
include(":feature_mover")
include(":feature_mechanic")
include(":feature_delivery")
include(":feature_handyman")
include(":feature_chat")
include(":feature_hotel")
include(":feature_babysitter")
include(":feature_housecleaning")
include(":feature_laundry")
include(":feature_pcrepair")
include(":feature_eat")
include(":feature_bank")
include(":common")
include(":navigation")
