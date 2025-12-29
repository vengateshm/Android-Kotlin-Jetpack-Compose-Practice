import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("kotlin-parcelize")
  id("kotlinx-serialization")
  alias(libs.plugins.compose.plugin)
}

android {
  namespace = "dev.vengateshm.samples.home"
  compileSdk = 36

  defaultConfig {
    minSdk = 23

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlin {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_17)
    }
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)

  implementation(libs.material)

  // Compose dependencies
  implementation(libs.androidx.activity.compose)

  // Compose
  implementation(libs.bundles.compose.beta)
  // Compose testing
  // Test rules and transitive dependencies:
  androidTestImplementation(libs.androidx.compose.beta.ui.test.junit4)
  // Needed for createAndroidComposeRule, but not createComposeRule:
  debugImplementation(libs.androidx.compose.beta.ui.test.manifest)

  implementation(libs.androidx.material.icons.extended.android)

  implementation(libs.androidx.lifecycle.runtime.compose.android)
  implementation(libs.runtime.livedata)
  // Compose Material3
  implementation(libs.androidx.material3)

  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.serialization.json)

  implementation(projects.feature.common)

  testImplementation(libs.junit)
  androidTestImplementation(libs.ext.junit)
  androidTestImplementation(libs.espresso.core)
}
