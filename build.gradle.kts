// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false

    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10" apply false

    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.dokka") version "1.9.10" // Needs KDoc comments to generate documentation
}

//subprojects {
//    apply(plugin = "org.jetbrains.dokka")
//}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("docs/html"))
}

rootProject.extra.set("composeCompilerVersion", "1.5.3")
rootProject.extra.set("composeVersion", "1.4.1")