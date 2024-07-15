import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
    kotlin("plugin.power-assert") version "2.0.0"
}

application {
    applicationDefaultJvmArgs = listOf("-ea")
//    applicationDefaultJvmArgs = listOf("-Dkotlinx.coroutines.debug=on")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
    }
}

kotlin {
    sourceSets.all {
        languageSettings.enableLanguageFeature("ExplicitBackingFields")
    }
    compilerOptions {
        freeCompilerArgs.add("-Xdebug")// Don't use in release builds
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.collections.immutable)
    implementation(platform(libs.arrow.stack))
    implementation(libs.arrow.kt.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    implementation(libs.guava)
    // https://mvnrepository.com/artifact/uy.kohesive.klutter/klutter-core
    implementation(libs.klutter.core)

    implementation(libs.gson)
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
    implementation(libs.kotlin.reflect)
    runtimeOnly(libs.kotlin.reflect)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.jaxb.runtime)

    implementation("dev.reformator.stacktracedecoroutinator:stacktrace-decoroutinator-jvm:2.3.8")

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testImplementation(libs.mockk)

    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
powerAssert {
    functions = listOf(
        "kotlin.assert",
        "kotlin.test.assertTrue",
        "kotlin.test.assertEquals",
        "kotlin.test.assertNull",
        "kotlin.require",
        "dev.vengateshm.kotlin_practice.power_assert.MyAssertScope.assert"
    )
}