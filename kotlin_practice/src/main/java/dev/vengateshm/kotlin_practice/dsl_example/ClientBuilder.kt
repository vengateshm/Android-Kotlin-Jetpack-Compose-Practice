package dev.vengateshm.kotlin_practice.dsl_example

import java.time.LocalDate

@ClientDsl
class ClientBuilder {
    var firstName: String = ""
    var lastName: String = ""
    var company: Company? = null
    var twitter: Twitter? = null
    var dob: LocalDate? = null
    fun build(): Client {
        return Client(
            firstName = firstName, lastName = lastName
        ).apply {
            if (this@ClientBuilder.company != null) company = this@ClientBuilder.company
            if (this@ClientBuilder.twitter != null) twitter = this@ClientBuilder.twitter
            if (this@ClientBuilder.dob != null) dob = this@ClientBuilder.dob
        }
    }
}