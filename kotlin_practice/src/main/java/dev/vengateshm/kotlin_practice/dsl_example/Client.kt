package dev.vengateshm.kotlin_practice.dsl_example

import java.time.LocalDate

class Client(
    val firstName: String,
    val lastName: String,
) {
    var company: Company? = null
    var twitter: Twitter? = null
    var dob: LocalDate? = null

    val helloMessage: String
        get() = "Foo"

    override fun toString(): String {
        return "firstName:$firstName\nlastName:$lastName\n" +
                "company:${company?.name}, ${company?.city}\n" +
                "twitter:${twitter?.handle}\n" +
                "dob:${dob}"
    }
}

@DslMarker
annotation class ClientDsl