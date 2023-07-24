package dev.vengateshm.kotlin_practice.dsl_example

@ClientDsl
class TwitterBuilder {
    var handle: String = ""

    fun build(): Twitter {
        return Twitter(handle)
    }
}