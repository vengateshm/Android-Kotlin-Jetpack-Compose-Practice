import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
}

android {
    namespace = "dev.vengateshm.compose_material3"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val apiKey = gradleLocalProperties(rootDir, project.providers).getProperty("GEMINI_API_KEY")
        buildConfigField("String", "GEMINI_API_KEY", apiKey)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // In App updates
    implementation(libs.app.update)
    implementation(libs.app.update.ktx)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    //Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(projects.appcore)

    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
    testImplementation(libs.bundles.kotest)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.espresso.idling.resource)
    //Koin
    androidTestImplementation(libs.koin.test)
    androidTestImplementation(libs.koin.android.test)
    //Mockk
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.bundles.kotest)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}