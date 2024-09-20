package dev.vengateshm.compose_material3.other_concepts.upload_file

data class ProgressUpdate(
    val bytesUploaded: Long,
    val totalBytes: Long,
)
