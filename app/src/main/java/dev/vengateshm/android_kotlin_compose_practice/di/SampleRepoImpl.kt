package dev.vengateshm.android_kotlin_compose_practice.di

class SampleRepoImpl : SampleRepo {
    override suspend fun getSampleString(): String {
        return "Sample"
    }
}