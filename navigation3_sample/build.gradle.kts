plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.compose.plugin)
  id("kotlin-parcelize")
  id("kotlinx-serialization")
  kotlin("plugin.serialization") version libs.versions.kotlin.get()
}

android {
  namespace = "dev.vengateshm.navigation3_sample"
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
  kotlinOptions {
    jvmTarget = "17"
  }
  buildFeatures {
    compose = true
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
  implementation(libs.androidx.fragment.compose)
  implementation(libs.androidx.compose.adaptive.navigation3)

  // Compose testing
  // Test rules and transitive dependencies:
  androidTestImplementation(libs.androidx.compose.beta.ui.test.junit4)
  // Needed for createAndroidComposeRule, but not createComposeRule:
  debugImplementation(libs.androidx.compose.beta.ui.test.manifest)

  implementation(libs.androidx.material.icons.extended.android)

  implementation(libs.androidx.lifecycle.runtime.compose.android)

  // Compose Material3
  implementation(libs.androidx.material3)
  implementation(libs.androidx.compose.material3.adaptive)
  implementation(libs.androidx.compose.material3.adaptive.layout)
  implementation(libs.androidx.compose.material3.adaptive.navigation)
  implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

  // Navigation3
  implementation(libs.androidx.navigation3.ui)
  implementation(libs.androidx.navigation3.runtime)
  implementation(libs.androidx.lifecycle.viewmodel.navigation3)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.kotlinx.serialization.core)
  implementation(libs.androidx.compose.adaptive.navigation3)

  testImplementation(libs.junit)
  androidTestImplementation(libs.ext.junit)
  androidTestImplementation(libs.espresso.core)
}