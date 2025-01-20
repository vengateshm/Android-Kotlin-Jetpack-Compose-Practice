import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

apply(from = "../versions.gradle")
apply(from = "../versioning.gradle.kts")

val composeCompilerVersion: String by rootProject.extra
val appVersionCode: Int by extra
val appVersionName: String by extra
println("App Version Code: $appVersionCode")
println("App Version Name: $appVersionName")

val vCode: Int by extra
val vName: String by extra
println("App Version Code: $vCode")
println("App Version Name: $vName")

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.protobuf") version "0.9.4"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("com.google.gms.google-services")
    id("com.apollographql.apollo3") version libs.versions.apolloRuntime.get()
    id("com.google.devtools.ksp")
//    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    alias(libs.plugins.compose.plugin)
}

android {
    namespace = "dev.vengateshm.android_kotlin_compose_practice"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.vengateshm.android_kotlin_compose_practice"
        minSdk = 23
        targetSdk = 34
        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val timeStamp =
            LocalDateTime
                .now()
                .format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh-mm-ss a"))
        val apkName = "AndroidKotlinComposePractice-$timeStamp"
        // val apkName = "$applicationId-$versionName"
        project.setProperty("archivesBaseName", apkName)
    }

    flavorDimensions += listOf("subscription_status"/* "style"*/)
    productFlavors {
        create("free") {
            applicationIdSuffix = ".free"
            dimension = "subscription_status"
        }
        create("paid") {
            applicationIdSuffix = ".paid"
            dimension = "subscription_status"
        }
        /*create("green") {
            dimension = "style"
        }
        create("red") {
            dimension = "style"
        }*/
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        create("staging") {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    metricsDestination = layout.buildDirectory.dir("compose_compiler")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Splash screen
    implementation(libs.androidx.core.splashscreen)

    // Material
    implementation(libs.material)

    // Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(libs.androidx.activity.compose)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.bundles.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
    // Compose testing
    // Test rules and transitive dependencies
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    // Needed for createAndroidComposeRule, but not createComposeRule
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // LiveData
    implementation(libs.androidx.runtime.livedata)
    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Constraint layout
    implementation(libs.androidx.constraintlayout.compose)
    // Google Maps
    implementation(libs.maps.compose)

    // Material icons
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)

    // Window manager
    implementation(libs.androidx.window)

    // Accompanist
    // Permissions
    implementation(libs.accompanist.permissions)
    // Pager
    implementation(libs.accompanist.pager)

    // Lottie
    implementation(libs.lottie.compose)

    implementation(libs.landscapist.glide)

    // Paging
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)
    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.moshi)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Kotlinx coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)

    // Google Maps
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.compose.material.dialogs)
    coreLibraryDesugaring(libs.desugar.jdk)

    implementation(libs.bundles.ktor.client)

    // Apollo GraphQL
    implementation(libs.apollo.runtime)

    // Firebase BOM
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.database.ktx)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)

    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

    // Play services
    implementation(libs.play.services.ads)

    // Arrow
    implementation(platform(libs.arrow.stack))
    implementation(libs.arrow.kt.arrow.core)

    // Immutable collections
    implementation(libs.kotlinx.collections.immutable)

    // Media3
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.hls)

    implementation(libs.androidx.biometric)
    implementation(libs.androidx.biometric.ktx)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    implementation(projects.kspProcessor)
    ksp(projects.kspProcessor)

    implementation(projects.appcore)
    implementation(projects.composeMaterial3)
    implementation(projects.glanceAppWidget)
    implementation(projects.xmlKotlin)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.room.testing)

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // To test flows
    androidTestImplementation(libs.turbine)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.2"
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
                create("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

apollo {
//    service("countries") {
//        srcDir("src/main/graphql/countries")
//        packageName.set("dev.vengateshm.android_kotlin_compose_practice")
//    }
    service("rickandmortyapi") {
        srcDir("src/main/graphql/rickandmortyapi")
        packageName.set("dev.vengateshm.android_kotlin_compose_practice")
    }
//    ./gradlew :app:downloadApolloSchema --endpoint="https://rickandmortyapi.com/graphql" --schema="app/src/main/graphql/rickandmortyapi/schema.graphqls"
}

kapt {
    correctErrorTypes = true
}

/*
tasks.register<Copy>("copyPreCommitHook") {
    description = "Copy pre-commit hook from scripts to the .git/hooks directory"
    group = "git-hooks"
    outputs.upToDateWhen { false }
    from("$rootDir/scripts/pre-commit")
    to("$rootDir/.git/hooks/")
}

tasks.build {
    dependsOn("copyPreCommitHook")
}*/
