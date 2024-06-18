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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
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

    implementation("androidx.navigation:navigation-compose:2.8.0-beta03")
    implementation(libs.kotlinx.serialization.json)

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

    implementation(libs.bundles.ktor.client)

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

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    //Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)

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
    // Compose testing
    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeNonStableVersion")
    // Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeNonStableVersion")

    androidTestImplementation("androidx.navigation:navigation-testing:2.7.7")
    androidTestImplementation(libs.bundles.kotest)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}