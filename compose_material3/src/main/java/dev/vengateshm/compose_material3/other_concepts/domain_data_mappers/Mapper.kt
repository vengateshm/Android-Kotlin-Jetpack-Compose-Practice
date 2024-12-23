package dev.vengateshm.compose_material3.other_concepts.domain_data_mappers

interface Mapper<F, T> {
    fun map(from: F): T
}

fun <F, T> Mapper<F, T>.mapAll(from: List<F>): List<T> = from.map { map(it) }