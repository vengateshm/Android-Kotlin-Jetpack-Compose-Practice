import org.gradle.kotlin.dsl.ksp
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.android.library)
  id("com.google.devtools.ksp")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "dev.vengateshm.testing"
  compileSdk = 37

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "dev.vengateshm.testing.HiltTestRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

  //Room
  implementation(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)
  // Kotlin Extensions and Coroutines support for Room
  implementation(libs.androidx.room.ktx)

  // Dagger - Hilt
  implementation(libs.hilt.android)
  ksp(libs.hilt.android.compiler)
  ksp(libs.androidx.hilt.compiler)

  testImplementation(libs.junit)
  androidTestImplementation(libs.ext.junit)
  androidTestImplementation(libs.espresso.core)
  androidTestImplementation(libs.androidx.core.testing)
  androidTestImplementation(libs.androidx.test.runner)
  androidTestImplementation(libs.androidx.test.rules)
  androidTestImplementation(libs.hilt.android.testing)
  kspAndroidTest(libs.hilt.android.compiler)
  androidTestImplementation(libs.kotlinx.coroutines.test)
  androidTestImplementation(libs.truth)
}