package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app

import dev.vengateshm.android_kotlin_compose_practice.GetCountriesByContinentQuery

data class Country(val name: String, val capital: String, val currency: String)

fun GetCountriesByContinentQuery.Country.toModel() = Country(
    name = name, capital = capital ?: "", currency = currency ?: ""
)
