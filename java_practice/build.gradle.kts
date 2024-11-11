plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.rxjava)
    implementation("com.google.dagger:dagger:2.49")
    annotationProcessor("com.google.dagger:dagger-compiler:2.49")
}
