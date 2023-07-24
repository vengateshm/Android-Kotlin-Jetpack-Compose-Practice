package dev.vengateshm.kotlin_practice.dsl_example

@ClientDsl
class CompanyBuilder {
    var name: String = ""
    var city: String = ""
    fun build(): Company {
        return Company(name, city)
    }
}