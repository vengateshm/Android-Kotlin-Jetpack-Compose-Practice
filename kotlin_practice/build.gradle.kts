import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  application
  id("java-library")
  id("org.jetbrains.kotlin.jvm")
  kotlin("plugin.serialization") version libs.versions.kotlin.get()
  kotlin("plugin.power-assert") version "2.0.0"
  id("com.google.devtools.ksp")
  id("org.jetbrains.kotlinx.benchmark") version "0.4.12"
}

application {
  applicationDefaultJvmArgs = listOf("-ea")
//    applicationDefaultJvmArgs = listOf("-Dkotlinx.coroutines.debug=on")
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
  compilerOptions {
    freeCompilerArgs.add("-Xcontext-receivers")
    freeCompilerArgs.add("-Xwhen-guards")
    freeCompilerArgs.add("-Xnon-local-break-continue")
    freeCompilerArgs.add("-Xmulti-dollar-interpolation")
    freeCompilerArgs.add("-Xcontext-sensitive-resolution")
  }
}

kotlin {
  jvmToolchain(21)
  sourceSets.all {
    languageSettings.enableLanguageFeature("ExplicitBackingFields")
  }
  compilerOptions {
    freeCompilerArgs.add("-Xdebug") // Don't use in release builds
    freeCompilerArgs.add("-Xcontext-sensitive-resolution")
  }
}

tasks.withType<KotlinCompile>().configureEach {
  compilerOptions {
    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21) // Specify the JVM version
  }
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.collections.immutable)
  implementation(platform(libs.arrow.stack))
  implementation(libs.arrow.kt.arrow.core)
  implementation(libs.arrow.fx.coroutines)
  implementation(libs.arrow.optics)
  implementation(libs.arrow.resilience)
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

  implementation(libs.kotlinx.datetime)

  implementation(projects.kspProcessor)
  ksp(projects.kspProcessor)

  ksp("io.arrow-kt:arrow-optics-ksp-plugin:${libs.versions.arrowCore.get()}")

  implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.12")

  implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
  implementation("com.squareup.moshi:moshi-adapters:1.15.1")

  implementation(libs.kotlindl.tensorflow)
  implementation(libs.kotlin.csv.jvm)

  implementation(libs.dataframe)
  implementation(libs.dataframe.csv)

  testImplementation(libs.junit)
  testImplementation(libs.junit.jupiter.api)
  testImplementation(libs.junit.jupiter.params)
  testRuntimeOnly(libs.junit.jupiter.engine)

  testImplementation(libs.mockk)

  testImplementation(libs.kotlin.test)
  testImplementation("net.jqwik:jqwik:1.9.0")
  testImplementation("io.strikt:strikt-core:0.34.0")
}

tasks.test {
  useJUnitPlatform()
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
powerAssert {
  functions =
    listOf(
      "kotlin.assert",
      "kotlin.test.assertTrue",
      "kotlin.test.assertEquals",
      "kotlin.test.assertNull",
      "kotlin.require",
      "dev.vengateshm.kotlin_practice.power_assert.MyAssertScope.assert",
    )
}

benchmark {
  targets.create("main")
}
