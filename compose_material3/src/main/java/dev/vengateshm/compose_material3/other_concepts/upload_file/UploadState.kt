package dev.vengateshm.compose_material3.other_concepts.upload_file

data class UploadState(
    val isUploading: Boolean = false,
    val isUploadComplete: Boolean = false,
    val progress: Float = 0f,
    val errorMessage: String? = null,
)
