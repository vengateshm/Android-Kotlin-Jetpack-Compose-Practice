package dev.vengateshm.compose_material3.di.koin

class KoinSampleRepoV2 : KoinSampleRepo {
    override fun foo(message: String) {
        println("KoinSampleRepoV2 foo $message")
    }
}