import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.Properties

val composeVersion: String by rootProject.extra
val composeCompilerVersion: String by rootProject.extra

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("kotlin-parcelize")
  id("kotlinx-serialization")
  kotlin("plugin.serialization") version libs.versions.kotlin.get()
//    alias(libs.plugins.composeInvestigator)
  alias(libs.plugins.compose.plugin)
  id("com.google.devtools.ksp")
  alias(libs.plugins.openApi.generator)
  kotlin("kapt")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "dev.vengateshm.compose_material3"
  compileSdk = 36

  val file = rootProject.file("local.properties")
  val properties = Properties()
  properties.load(FileInputStream(file))
  val testApiKey = properties.getProperty("testApiKey")
  println(testApiKey)

  defaultConfig {
    minSdk = 23

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val geminiApiKey =
      gradleLocalProperties(rootDir, project.providers).getProperty("GEMINI_API_KEY")
    buildConfigField("String", "GEMINI_API_KEY", geminiApiKey)
    buildConfigField("String", "testApiKey", testApiKey)

    val localProperties = Properties()
    val localPropertiesFile = File("$rootDir/secret.properties")
    if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
      localPropertiesFile.inputStream().use {
        localProperties.load(it)
      }
    }
    buildConfigField("String", "API_KEY", localProperties.getProperty("API_KEY"))
    buildConfigField("String", "API_KEY_PROD", localProperties.getProperty("API_KEY_PROD"))
  }

  buildFeatures {
    buildConfig = true
    resValues = true
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
    debug {
      isMinifyEnabled = false
      resValue("string", "api_url", "https://api.dev.example.com")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
      excludes += "/META-INF/LICENSE.md"
      excludes += "/META-INF/LICENSE-notice.md"
      excludes += "**/attach_hotspot_windows.dll"
      excludes += "META-INF/licenses/ASM"
    }
  }
  resourcePrefix = "cmaterial3_"
}

tasks.withType<KotlinCompile>().configureEach {
  compilerOptions {
    freeCompilerArgs.add("-Xcontext-receivers")
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
  implementation(libs.androidx.compose.animation.graphics)
  implementation("androidx.compose.runtime:runtime-retain:1.10.0")

  // Compose testing
  // Test rules and transitive dependencies:
  androidTestImplementation(libs.androidx.compose.beta.ui.test.junit4)
  // Needed for createAndroidComposeRule, but not createComposeRule:
  debugImplementation(libs.androidx.compose.beta.ui.test.manifest)

  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.hilt.navigation.compose)

  implementation(libs.androidx.material.icons.extended.android)

  implementation(libs.androidx.lifecycle.runtime.compose.android)
  implementation(libs.runtime.livedata)
  // Compose Material3
  implementation(libs.androidx.material3)
  implementation(libs.androidx.compose.material3.adaptive)
  implementation(libs.androidx.compose.material3.adaptive.layout)
  implementation(libs.androidx.compose.material3.adaptive.navigation)
  implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

  implementation(libs.androidx.preference.ktx)
  implementation(libs.androidx.security.crypto.ktx)

  implementation(libs.kotlinx.serialization.json)

  implementation(libs.androidx.datastore)
  implementation(libs.androidx.datastore.core)
  implementation(libs.androidx.datastore.preferences)

  // Voyager
  implementation(libs.voyager.navigator)
  implementation(libs.voyager.tabNavigator)
  implementation(libs.voyager.transitions)

  implementation(libs.generativeai)
  implementation(libs.play.services.mlkit.document.scanner)
  implementation(libs.play.services.location)

  implementation(libs.bundles.ktor.client)

  implementation(libs.retrofit)
  implementation(libs.converter.gson)
  implementation(libs.converter.moshi)
  implementation(libs.converter.scalars)

  implementation(libs.okhttp)
  implementation(libs.logging.interceptor)

  implementation(libs.gson)

  // In App updates
  implementation(libs.app.update)
  implementation(libs.app.update.ktx)

  //Room
  implementation(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)
  // Kotlin Extensions and Coroutines support for Room
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.paging)

  //Koin
  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
  implementation(libs.koin.android)
  implementation(libs.koin.androidx.compose)
  // Koin Annotations
  implementation(libs.koin.annotations)
  // Koin Annotations KSP Compiler
  ksp(libs.koin.ksp.compiler)

  implementation(libs.fig)

  implementation(libs.libphonenumber.android)

  implementation(libs.androidx.camera.core)
  implementation(libs.androidx.camera.camera2)
  implementation(libs.androidx.camera.lifecycle)
  implementation(libs.androidx.camera.video)

  implementation(libs.androidx.camera.view)
  implementation(libs.androidx.camera.extensions)

  implementation(libs.tensorflow.lite.task.vision)
  implementation(libs.tensorflow.lite.gpu.delegate.plugin)
  implementation(libs.tensorflow.lite.gpu)

  // Paging
  implementation(libs.androidx.paging.runtime.ktx)
  implementation(libs.androidx.paging.compose)

  implementation(libs.kotlinx.datetime)

  implementation(projects.appcore)
  implementation(projects.feature.common)
  implementation(projects.feature.auth)
  implementation(projects.feature.home)
  implementation(projects.koinSdkSample)

  implementation(libs.jsoup)

  implementation(libs.androidx.credentials.core)
  implementation(libs.androidx.credentials.compat)
  implementation(libs.googleid)

  implementation(libs.androidx.appsearch)
  kapt(libs.androidx.appsearch.compiler)
  implementation(libs.androidx.appsearch.local.storage)

  implementation("net.zetetic:android-database-sqlcipher:4.5.3")
  implementation("androidx.sqlite:sqlite:2.4.0")

  implementation(libs.play.services.mlkit.smart.reply)

  // Firebase BOM
  implementation(platform(libs.firebase.bom))
  implementation(libs.firebase.auth)

  // Dagger - Hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.android.compiler)
  kapt(libs.androidx.hilt.compiler)

  implementation(libs.androidx.palette.ktx)

  implementation(libs.androidx.constraintlayout.compose)

  implementation(libs.lottie.compose)

  implementation(libs.bundles.coil3)

  implementation(libs.androidx.work.runtime.ktx)

  implementation(libs.kotlinx.collections.immutable)

  // Media3
  implementation(libs.androidx.media3.ui)
  implementation(libs.androidx.media3.exoplayer)
  implementation(libs.androidx.media3.exoplayer.hls)

  testImplementation(libs.junit)
  testImplementation(libs.koin.test)
  testImplementation(libs.koin.test.junit4)
  testImplementation(libs.bundles.kotest)
  testImplementation(libs.mockk)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.turbine)
  testImplementation(libs.truth)

  androidTestImplementation(libs.ext.junit)
  androidTestImplementation(libs.espresso.core)
  androidTestImplementation(libs.androidx.espresso.idling.resource)
  //Koin
  androidTestImplementation(libs.koin.test)
  androidTestImplementation(libs.koin.test.junit4)
  androidTestImplementation(libs.koin.android.test)
  //Mockk
  androidTestImplementation(libs.mockk.android)
  androidTestImplementation(libs.androidx.navigation.testing)
  androidTestImplementation(libs.bundles.kotest)
  androidTestImplementation(libs.kotlinx.coroutines.test)
}

// Uncomment when running tests with JUnit5
/*
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}*/

openApiGenerate {
  skipValidateSpec.set(true)
  packageName.set("dev.vengateshm.compose_material3.openapi")
  generatorName.set("kotlin")
  generateApiTests.set(false)
  generateModelTests.set(false)
  library.set("jvm-retrofit2")
  inputSpec.set("$rootDir/api.yml")
  configOptions.set(
    mapOf(
      "serializationLibrary" to "gson",
      "useCoroutines" to "true",
    ),
  )
}

// To access in app/module directory
kotlin {
  sourceSets {
    main {
      kotlin.srcDir("$rootDir/compose_material3/build/generate-resources/main/src")
    }
  }
}

// Generate openapi code while building
/*
tasks.withType<KotlinCompile>().configureEach {
    dependsOn("openApiGenerate")
}*/
