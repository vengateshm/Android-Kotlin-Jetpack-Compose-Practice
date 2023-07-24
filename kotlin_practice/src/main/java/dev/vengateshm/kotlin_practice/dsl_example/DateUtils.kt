package dev.vengateshm.kotlin_practice.dsl_example

import java.time.LocalDate
import java.time.Month

typealias LD = LocalDate

infix fun Int.July(year: Int) = LD.of(year, Month.JULY, this)