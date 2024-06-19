import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
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
    runtimeOnly(libs.kotlin.reflect)

    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testImplementation(libs.mockk)
}

tasks.test {
    useJUnitPlatform()
}