 pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven{
            setUrl("https://jitpack.io")
        }
    }
}

rootProject.name = "Countrivia"
include(":app")
