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

rootProject.name = "AndroidKotlinComposePractice"
include(":app")
include(":compose_material3")
include(":common_lib")
include(":glance_app_widget")
include(":kotlin_practice")
include(":java_practice")
include(":ksp_samples")
