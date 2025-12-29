import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.compose.plugin)
}

android {
  namespace = "dev.vengateshm.wearosapp"
  compileSdk = 36

  defaultConfig {
    applicationId = "dev.vengateshm.wearosapp"
    minSdk = 30
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  buildFeatures {
    compose = true
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    isCoreLibraryDesugaringEnabled = true
  }
  kotlin {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_17)
      freeCompilerArgs.add("-Xopt-in=kotlin.RequiresOptIn")
    }
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(libs.play.services.wearable)
  implementation(libs.androidx.activity.compose)

  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)

  implementation(libs.bundles.compose)
  implementation(libs.androidx.material.icons.extended)

  implementation(libs.wear.compose.material)
  implementation(libs.wear.compose.foundation)

  implementation(libs.androidx.compose.wear.ui.tooling)
  implementation(libs.horologist.compose.layout)

  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
  debugImplementation(composeBom)
}