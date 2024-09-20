package dev.vengateshm.compose_material3.other_concepts.upload_file

import android.net.Uri
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class UploadFileRepository(
    private val httpClient: HttpClient,
    private val fileReader: FileReader,
) {
    fun uploadFile(contentUri: Uri): Flow<ProgressUpdate> = channelFlow {
        val fileInfo = fileReader.uriToFileInfo(contentUri)

        httpClient.submitFormWithBinaryData(
            url = "https://dlptest.com/https-post/",
            formData = formData {
                append("description", "Test")
                append(
                    "the_file", fileInfo.bytes,
                    Headers.build {
                        append(HttpHeaders.ContentType, fileInfo.mimeType)
                        append(HttpHeaders.ContentDisposition, "filename=${fileInfo.name}")
                    },
                )
            },
        ) {
            onUpload { bytesSentTotal, totalBytes ->
                if (totalBytes > 0L) {
                    val progress = ProgressUpdate(
                        bytesUploaded = bytesSentTotal,
                        totalBytes = totalBytes,
                    )
                    send(progress)
                }
            }
        }
    }
}