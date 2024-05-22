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
    alias(libs.plugins.composeInvestigator)
}

android {
    namespace = "dev.vengateshm.compose_material3"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    resourcePrefix = "cmaterial3_"
}

tasks.withType(KotlinCompile::class.java) {
    kotlinOptions.freeCompilerArgs += "-Xcontext-receivers"
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("com.google.android.material:material:1.11.0")

    // Compose dependencies
    implementation("androidx.activity:activity-compose:1.8.2")

    val composeNonStableVersion = "1.7.0-beta01"
    implementation("androidx.compose.ui:ui:$composeNonStableVersion")
    implementation("androidx.compose.foundation:foundation:$composeNonStableVersion")
    implementation("androidx.compose.animation:animation:$composeNonStableVersion")
    implementation("androidx.compose.runtime:runtime:$composeNonStableVersion")
    implementation("androidx.compose.foundation:foundation-layout:$composeNonStableVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeNonStableVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeNonStableVersion")

    implementation(libs.androidx.preference.ktx)
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")

    implementation("androidx.compose.runtime:runtime-livedata")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Compose Material3
    val material3Version = "1.2.1"
    implementation("androidx.compose.material3:material3:$material3Version")
    implementation("androidx.compose.material3:material3-window-size-class:$material3Version")
    implementation("androidx.compose.material3:material3-adaptive:1.0.0-alpha06")
//    implementation("androidx.compose.material3:material3-adaptive-navigation-suite-android:1.0.0-alpha05")

    implementation("androidx.compose.material:material-icons-extended-android:1.6.2")

    implementation("androidx.navigation:navigation-compose:2.8.0-beta01")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("androidx.lifecycle:lifecycle-runtime-compose-android:2.8.0-rc01")

    // FlowLayout
    implementation("com.google.accompanist:accompanist-flowlayout:0.23.1")
    implementation(project(":appcore"))

    // Voyager
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.tabNavigator)
    implementation(libs.voyager.transitions)

    implementation("com.google.ai.client.generativeai:generativeai:0.2.1")

    implementation("com.google.android.gms:play-services-mlkit-document-scanner:16.0.0-beta1")

    implementation("com.google.android.gms:play-services-location:21.2.0")

    implementation("io.ktor:ktor-client-core:2.3.9")
    implementation("io.ktor:ktor-client-android:2.3.9")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.9")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.9")
    implementation("io.ktor:ktor-client-logging:2.3.9")

    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    val okhttpVersion = "5.0.0-alpha.3"
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")

    // In App updates
    implementation("com.google.android.play:app-update:2.1.0")
    implementation("com.google.android.play:app-update-ktx:2.1.0")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Compose testing
    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeNonStableVersion")
    // Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeNonStableVersion")
}