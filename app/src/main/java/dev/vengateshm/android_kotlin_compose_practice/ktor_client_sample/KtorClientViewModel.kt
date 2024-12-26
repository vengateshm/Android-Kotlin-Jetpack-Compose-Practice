package dev.vengateshm.android_kotlin_compose_practice.ktor_client_sample

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class KtorClientViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    var isUploading = mutableStateOf(false)
    var transferred = mutableStateOf("")
    var downloadedFile = mutableStateOf<File?>(null)

    fun uploadFile(file: File) {
        viewModelScope.launch(dispatcher) {
            isUploading.value = true
            KtorApiClient.client.post("http://192.168.240.241:9999/upload-file") {
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append(
                                "image",
                                file.readBytes(),
                                Headers.build {
                                    append(
                                        HttpHeaders.ContentType,
                                        "images/*",
                                    )
                                    append(
                                        HttpHeaders.ContentDisposition,
                                        "filename=sample.jpg",
                                    )
                                },
                            )
                        },
                        boundary = "WebAppBoundary",
                    ),
                )
                onUpload { bytesSentTotal, contentLength ->
                    if (contentLength != null) {
                        transferred.value = "Uploaded ${"%.2f".format(bytesSentTotal.toMB())}/${
                            "%.2f".format(contentLength.toMB())
                        } MB"
                    }
                }
            }
            isUploading.value = false
        }
    }

    fun downloadImage(file: File) {
        viewModelScope.launch(dispatcher) {
            isUploading.value = true
            val httpResponse: HttpResponse =
                KtorApiClient.client.get("http://192.168.240.241:9999/sample-image") {
                    onDownload { bytesSentTotal, contentLength ->
                        if (contentLength != null) {
                            transferred.value =
                                "Downloaded ${"%.2f".format(bytesSentTotal.toMB())}/${
                                    "%.2f".format(contentLength.toMB())
                                } MB"
                        }
                    }
                }
            val responseBody: ByteArray = httpResponse.body()
            if (responseBody.isNotEmpty()) {
                file.writeBytes(responseBody)
                downloadedFile.value = file
            }
            isUploading.value = false
        }
    }
}

fun Long.toMB(): Double {
    return this.toDouble() / (1024.0 * 1024.0)
}
