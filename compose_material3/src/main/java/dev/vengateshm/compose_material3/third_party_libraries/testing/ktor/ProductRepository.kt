package dev.vengateshm.compose_material3.third_party_libraries.testing.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class ProductRepository(
    private val client: HttpClient,
) {

    private val baseUrl = "https://dummyjson.com/products"

    suspend fun getProducts(): List<Product> {
        val response = try {
            client.get(
                urlString = baseUrl,
            )
        } catch (e: UnresolvedAddressException) {
            return emptyList()
        }

        if (response.status.value >= 400) {
            return emptyList()
        }

        val body = try {
            response.body<ProductResponse>()
        } catch (e: SerializationException) {
            return emptyList()
        }

        return body.products.map {
            Product(
                id = it.id,
                title = it.title,
                description = it.description,
                category = it.category,
                price = it.price,
                discountPercentage = it.discountPercentage,
                rating = it.rating,
                stock = it.stock,
                tags = it.tags,
                brand = it.brand,
                sku = it.sku,
                weight = it.weight,
                dimensions = it.dimensions,
                warrantyInformation = it.warrantyInformation,
                shippingInformation = it.shippingInformation,
                availabilityStatus = it.availabilityStatus,
                reviews = it.reviews,
                returnPolicy = it.returnPolicy,
                minimumOrderQuantity = it.minimumOrderQuantity,
                meta = it.meta,
                images = it.images,
                thumbnail = it.thumbnail,
            )
        }
    }

    suspend fun getProductById(productId: Int): Product? {
        return try {
            // Assuming your API supports fetching a single product like this
            client.get("$baseUrl/$productId").body<Product>()
        } catch (e: Exception) {
            println("Error fetching product by ID $productId: ${e.message}")
            null
        }
    }

    fun close() {
        client.close()
    }
}