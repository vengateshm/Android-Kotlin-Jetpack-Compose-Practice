package dev.vengateshm.kotlin_practice.polymorphic_json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.vengateshm.kotlin_practice.polymorphic_json.Item.Book
import dev.vengateshm.kotlin_practice.polymorphic_json.Item.Car
import dev.vengateshm.kotlin_practice.polymorphic_json.Item.Person
import java.lang.reflect.ParameterizedType

fun main() {
    val simpleJsonRepository = SimpleJsonRepository()
    val simpleJson = simpleJsonRepository.getJson()
    val polymorphicJsonRepository = PolymorphicJsonRepository()
    val polymorphicJson = polymorphicJsonRepository.getJson()

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val countryListType: ParameterizedType =
        Types.newParameterizedType(List::class.java, Country::class.java)
    val countryAdapter: JsonAdapter<List<Country>> = moshi.adapter(countryListType)
    val countryList = countryAdapter.fromJson(simpleJson)!!
    countryList.forEach(::println)

    val getAllCountriesResponseAdapter: JsonAdapter<GetAllCountriesResponse> =
        moshi.adapter(GetAllCountriesResponse::class.java)
    val countries =
        getAllCountriesResponseAdapter.fromJson(simpleJsonRepository.getJsonResponse())!!.countries
    countries.forEach(::println)

    val polymorphicJsonAdapterFactory = PolymorphicJsonAdapterFactory.of(Item::class.java, "type")
        .withSubtype(Person::class.java, "p").withSubtype(Car::class.java, "c")
        .withSubtype(Book::class.java, "b")
    val moshi1 =
        Moshi.Builder().add(polymorphicJsonAdapterFactory).addLast(KotlinJsonAdapterFactory())
            .build()

    val itemListType: ParameterizedType =
        Types.newParameterizedType(List::class.java, Item::class.java)
    val itemListAdapter: JsonAdapter<List<Item>> = moshi1.adapter(itemListType)
    val itemList = itemListAdapter.fromJson(polymorphicJson)!!
    itemList.forEach(::println)
}