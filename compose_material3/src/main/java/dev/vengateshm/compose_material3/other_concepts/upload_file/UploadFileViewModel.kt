package dev.vengateshm.compose_material3.other_concepts.upload_file

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import java.io.FileNotFoundException
import kotlin.coroutines.cancellation.CancellationException

class UploadFileViewModel(
    private val repository: UploadFileRepository,
) : ViewModel() {

    var state by mutableStateOf(UploadState())
        private set

    private var uploadJob: Job? = null

    fun uploadFile(contentUri: Uri) {
        uploadJob = repository
            .uploadFile(contentUri)
            .onStart {
                state = state.copy(
                    isUploading = true,
                    isUploadComplete = false,
                    progress = 0f,
                    errorMessage = null,
                )
            }
            .onEach { progressUpdate ->
                state = state.copy(
                    isUploading = true,
                    isUploadComplete = false,
                    progress = progressUpdate.bytesUploaded / progressUpdate.totalBytes.toFloat(),
                    errorMessage = null,
                )
            }
            .onCompletion { error ->
                if (error == null) {
                    state = state.copy(
                        isUploading = false,
                        isUploadComplete = true,
                    )
                } else if (error is CancellationException) {
                    state = state.copy(
                        isUploading = false,
                        isUploadComplete = false,
                        errorMessage = "The upload was cancelled!",
                        progress = 0f,
                    )
                }
            }
            .catch { cause ->
                val message = when (cause) {
                    is OutOfMemoryError -> "File too large!"
                    is FileNotFoundException -> "File not found!"
                    is UnresolvedAddressException -> "No internet!"
                    else -> "Something went wrong!"
                }
                state = state.copy(
                    isUploading = false,
                    errorMessage = message,
                )
            }
            .launchIn(viewModelScope)
    }

    fun cancelUpload() {
        uploadJob?.cancel()
    }
}

class UploadFileViewModelFactory(
    private val repository: UploadFileRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UploadFileViewModel::class.java)) {
            return UploadFileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
