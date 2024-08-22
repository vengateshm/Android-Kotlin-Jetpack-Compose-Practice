plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.devtools.ksp)
}

dependencies {
    implementation(libs.ksp.api)
    api(projects.kspAnnotations)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}