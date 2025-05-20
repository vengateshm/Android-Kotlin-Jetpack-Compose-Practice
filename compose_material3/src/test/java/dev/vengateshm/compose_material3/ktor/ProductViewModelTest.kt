package dev.vengateshm.compose_material3.ktor

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.vengateshm.compose_material3.third_party_libraries.testing.ktor.HttpClientFactory
import dev.vengateshm.compose_material3.third_party_libraries.testing.ktor.ProductRepository
import dev.vengateshm.compose_material3.third_party_libraries.testing.ktor.ProductViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductViewModelTest {
    private lateinit var viewModel: ProductViewModel
    private lateinit var repository: ProductRepository
    private lateinit var httpClient: HttpClient
    private val testDispatcher = UnconfinedTestDispatcher()

    private var responseData = HttpResponseData(
        content = ProductsResponses.valid,
        statusCode = HttpStatusCode.OK,
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        httpClient = HttpClientFactory.create(
            engine = MockEngine.create {
                dispatcher = testDispatcher
                addHandler { request ->
                    val relativeUrl = request.url.encodedPath
                    when (relativeUrl) {
                        "/products" -> respond(
                            content = responseData.content,
                            status = responseData.statusCode,
                            headers = headers {
                                set("Content-Type", "application/json")
                            },
                        )

                        else -> respond(
                            content = "",
                            status = HttpStatusCode.NotFound,
                        )
                    }
                }
            },
        )
        repository = ProductRepository(httpClient)
        viewModel = ProductViewModel(repository)
    }

    @Test
    fun `Product API call success returns valid products`() {
        runBlocking {
            viewModel.products.test {
                val initialItems = awaitItem()
                assertThat(initialItems).isEmpty()

                val loadedProducts = awaitItem()
                assertThat(loadedProducts).hasSize(30)
            }
        }
    }

    @Test
    fun `Product API error returns empty list`() {
        responseData = HttpResponseData(
            content = "error",
            statusCode = HttpStatusCode.Forbidden,
        )
        runBlocking {
            viewModel.products.test {
                val initialItems = awaitItem()
                assertThat(initialItems).isEmpty()

                expectNoEvents()
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}