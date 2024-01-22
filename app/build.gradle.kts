val composeCompilerVersion: String by rootProject.extra

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.protobuf") version "0.9.4"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
//    id("com.google.gms.google-services")
    id("com.apollographql.apollo3") version "3.8.2"
    id("com.google.devtools.ksp")
//    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

android {
    namespace = "dev.vengateshm.android_kotlin_compose_practice"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.vengateshm.android_kotlin_compose_practice"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Splash screen
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")

    // [Start] Compose BOM
    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Compose dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.runtime:runtime")
    // Material icons
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.foundation:foundation-layout")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.ui:ui-tooling-preview")
    // LiveData
    implementation("androidx.compose.runtime:runtime-livedata")
    debugImplementation("androidx.compose.ui:ui-tooling")
    // Compose testing
    // Test rules and transitive dependencies
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // Needed for createAndroidComposeRule, but not createComposeRule
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Constraint layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation("com.google.android.material:material:1.11.0")
    implementation("com.google.maps.android:maps-compose:1.0.0")

    // Window manager
    implementation("androidx.window:window:1.2.0")

    // Accompanist
    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.21.2-beta")
    // Pager
    implementation("com.google.accompanist:accompanist-pager:0.13.0")
    // FlowLayout
    implementation("com.google.accompanist:accompanist-flowlayout:0.23.1")
    // Lottie
    implementation("com.airbnb.android:lottie-compose:4.2.0")
    implementation("androidx.compose.foundation:foundation:1.5.4")

    implementation("com.github.skydoves:landscapist-glide:1.4.5")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
    // Google Fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")

    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    val okhttpVersion = "5.0.0-alpha.3"
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")

    // Kotlinx coroutines
    val coroutinesVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    val coroutinesAndroidVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion")

    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.25.2")
    implementation("com.google.protobuf:protobuf-kotlin-lite:3.25.2")

    // Google Maps
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    implementation("io.github.vanpra.compose-material-dialogs:datetime:0.8.1-rc")
    coreLibraryDesugaring("com.android.tools.desugar_jdk_libs:1.1.6")

    val ktorVersion = "2.3.2"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    // Apollo GraphQL
    implementation("com.apollographql.apollo3:apollo-runtime:3.8.2")

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
    implementation("com.google.firebase:firebase-database-ktx")

    val cameraxVersion = "1.4.0-alpha03"
    implementation("androidx.camera:camera-camera2:$cameraxVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation("androidx.camera:camera-view:$cameraxVersion")

    implementation(project(":compose_material3"))
    implementation(project(":common_lib"))
    implementation(project(":glance_app_widget"))

    implementation(project(":ksp_samples"))
    ksp(project(":ksp_samples"))

    // Play services
    implementation("com.google.android.gms:play-services-ads:22.6.0")

    // Arrow
    implementation(platform("io.arrow-kt:arrow-stack:1.2.0-RC"))
    implementation("io.arrow-kt:arrow-core")

    // Immutable collections
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.room:room-testing:2.6.1")

    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // To test flows
    androidTestImplementation("app.cash.turbine:turbine:0.9.0")
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