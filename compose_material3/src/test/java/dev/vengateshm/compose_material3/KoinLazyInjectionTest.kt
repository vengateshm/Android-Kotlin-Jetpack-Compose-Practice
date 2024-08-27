package dev.vengateshm.compose_material3

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import kotlin.test.assertEquals

class MyService {
    private var value = 0

    fun store(value: Int) {
        this.value = value
    }

    fun retrieve() = value
}

val serModule = module {
    single { MyService() }
}

class KoinLazyInjectionTest {

    private val koin = koinApplication { modules(serModule) }.koin

    private val myService: MyService by koin.inject()

    @Test
    fun `test koin lazy injection`() {
        myService.store(5)
        assertEquals(5, myService.retrieve())
    }
}