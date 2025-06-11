enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
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
        maven { url = uri("https://jitpack.io") }
    }
}

//buildCache {
//    local {
//        isEnabled = true
//    }
//}

rootProject.name = "AndroidKotlinComposePractice"
include(":app")
include(":compose_material3")
include(":appcore")
include(":glance_app_widget")
include(":kotlin_practice")
include(":java_practice")
include(":ksp_processor")
include(":kotlin_gradle_samples")
include(":xml_kotlin")

// Building other projects and including
//includeBuild()
include(":feature:auth")
include(":feature:home")
include(":feature:common")
include(":home")
include(":booking")
include(":commonui")
include(":navigation3_sample")
include(":confetti_animation")
