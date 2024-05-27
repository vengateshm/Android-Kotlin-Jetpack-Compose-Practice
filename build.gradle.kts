// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.mapsplatform.secrets.gradle.plugin) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.jetbrains.dokka)// Needs KDoc comments to generate documentation
//    alias(libs.plugins.composeInvestigator) apply false
    alias(libs.plugins.compose.plugin) apply false
}

//subprojects {
//    apply(plugin = "org.jetbrains.dokka")
//}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("docs/html"))
}

rootProject.extra.set("composeCompilerVersion", "1.5.11")
rootProject.extra.set("composeVersion", "1.6.7")