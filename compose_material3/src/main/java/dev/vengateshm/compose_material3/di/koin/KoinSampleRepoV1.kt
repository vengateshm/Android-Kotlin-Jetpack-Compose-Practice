package dev.vengateshm.compose_material3.di.koin

class KoinSampleRepoV1 : KoinSampleRepo {
    override fun foo(message: String) {
        println("KoinSampleRepoV1 $message")
    }
}