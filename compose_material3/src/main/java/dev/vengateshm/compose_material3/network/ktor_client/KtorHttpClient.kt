package dev.vengateshm.compose_material3.network.ktor_client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

object KtorHttpClient {
  const val TIMEOUT_IN_MILLIS = 30_000L

  val okHttpClient = HttpClient(provideEngine()) {
    install(Logging) {
      logger =
        object : Logger {
          override fun log(message: String) {
            Log.v("HTTP Client", message)
          }
        }
      level = LogLevel.ALL
    }

    install(ContentNegotiation) {
      json(
        Json {
          prettyPrint = true
          isLenient = true
          ignoreUnknownKeys = true
        },
      )
    }

    HttpResponseValidator {
      validateResponse { response ->
        val statusCode = response.status.value
        if (statusCode >= 400) {
          throw ResponseException(response, "HTTP status code: $statusCode")
        }
      }

      handleResponseExceptionWithRequest { cause, _ ->
        when (cause) {
          is ClientRequestException -> {
            Log.e("Ktor Http Client", "Client error: ${cause.message}")
          }

          is ServerResponseException -> {
            Log.e("Ktor Http Client", "Server error: ${cause.message}")
          }

          is ResponseException -> {
            Log.e("Ktor Http Client", "Response error: ${cause.message}")
          }

          is IOException -> {
            Log.e("Ktor Http Client", "IO error: ${cause.message}")
          }

          else -> {
            Log.e("Ktor Http Client", "Unknown error: ${cause.message}")
          }
        }
      }
    }

    install(ResponseObserver) {
      onResponse { response ->
        Log.d("HTTP Client", "Response: ${response.status.value}")
      }
    }
  }

  fun provideEngine(): HttpClientEngine {
    return OkHttp.create {
      config {
        connectTimeout(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS)
        readTimeout(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS)
        writeTimeout(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS)

        addInterceptor(
          HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
          },
        )
      }
    }
  }
}