package dev.vengateshm.compose_material3.api_android.app_search

import android.content.Context
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.SearchSpec
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoffeeSearchManager(private val context: Context) {
    private var session: AppSearchSession? = null

    suspend fun initialize() {
        withContext(Dispatchers.IO) {
            val searchSession = LocalStorage.createSearchSessionAsync(
                LocalStorage.SearchContext.Builder(
                    context, "coffee_database",
                ).build(),
            )
            val schema = SetSchemaRequest.Builder()
                .addDocumentClasses(Coffee::class.java)
                .build()
            session = searchSession.get()
            session?.setSchemaAsync(schema)
        }
    }

    suspend fun addCoffeeList(coffeeList: List<Coffee>): Boolean {
        return withContext(Dispatchers.IO) {
            val putRequest = PutDocumentsRequest.Builder()
                .addDocuments(coffeeList)
                .build()
            session?.putAsync(putRequest)?.get()?.isSuccess ?: true
        }
    }

    suspend fun searchCoffee(query: String): List<Coffee> {
        return withContext(Dispatchers.IO) {
            val searchSpec = SearchSpec.Builder()
                .addFilterNamespaces("coffee")
                .setSnippetCount(15)
                .build()
            val results =
                session?.search(query, searchSpec) ?: return@withContext emptyList<Coffee>()
            val page = results.nextPageAsync.get()
            page.mapNotNull {
                if (it.genericDocument.schemaType == Coffee::class.java.simpleName) {
                    it.getDocument(Coffee::class.java)
                } else {
                    null
                }
            }
        }
    }

    fun closeSession() {
        session?.close()
    }
}