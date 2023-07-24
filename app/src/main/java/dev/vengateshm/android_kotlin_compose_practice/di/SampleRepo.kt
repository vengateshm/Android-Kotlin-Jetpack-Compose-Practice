package dev.vengateshm.android_kotlin_compose_practice.di

interface SampleRepo {
    suspend fun getSampleString(): String
}