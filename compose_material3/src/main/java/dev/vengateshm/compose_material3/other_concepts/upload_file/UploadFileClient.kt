package dev.vengateshm.compose_material3.other_concepts.upload_file

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

object UploadFileClient {
    val client by lazy {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
        }
    }
}