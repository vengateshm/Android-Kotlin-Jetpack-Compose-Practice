import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    alias(libs.plugins.openApi.generator) apply false
    alias(libs.plugins.spotless) apply false
    kotlin("kapt") version "2.0.10" apply false
}

val ktlintVersion = libs.versions.ktlint.get()

subprojects {
    //apply(plugin = "org.jetbrains.dokka")
    apply<SpotlessPlugin>()
    configure<SpotlessExtension> {
        kotlin {
            target("src/**/*.kt")
            targetExclude("build/**/*.kt")
            ktlint(ktlintVersion)
                .editorConfigOverride(
                    mapOf(
                        "ktlint_standard_no-wildcard-imports" to "disabled",
                        "ktlint_standard_package-name" to "disabled",
                        "ktlint_standard_function-naming" to "disabled",
                    ),
                )
            //this.ratchetFrom("origin/develop")
        }
        kotlinGradle {
            target("*.kts")
            ktlint(ktlintVersion)
        }
    }

    afterEvaluate {
        tasks.withType<KotlinCompile> {
            // Enable spotlessApply after KotlinCompile
            //finalizedBy("spotlessApply")
        }
    }
}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("docs/html"))
}

rootProject.extra.set("composeCompilerVersion", "1.5.11")
rootProject.extra.set("composeVersion", "1.6.7")