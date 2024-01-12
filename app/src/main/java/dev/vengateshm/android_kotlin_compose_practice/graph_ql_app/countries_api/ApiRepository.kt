package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.countries_api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dev.vengateshm.android_kotlin_compose_practice.GetAllCountriesQuery
import dev.vengateshm.android_kotlin_compose_practice.GetCountriesByContinentQuery
import dev.vengateshm.android_kotlin_compose_practice.type.CountryFilterInput
import dev.vengateshm.android_kotlin_compose_practice.type.StringQueryOperatorInput

class ApiRepository {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    suspend fun getContinents(): List<Continent> {
        return apolloClient.query(GetAllCountriesQuery()).execute().data
            ?.continents?.map { it.toModel() } ?: emptyList()
    }

    suspend fun getCountriesByContinent(continentCode: String): List<Country> {
        return apolloClient.query(
            GetCountriesByContinentQuery(
                countryInput = Optional.present(
                    CountryFilterInput(
                        continent = Optional.present(
                            StringQueryOperatorInput(
                                eq = Optional.present(continentCode)
                            )
                        )
                    )
                )
            )
        ).execute().data
            ?.countries?.map { it.toModel() } ?: emptyList()
    }
}