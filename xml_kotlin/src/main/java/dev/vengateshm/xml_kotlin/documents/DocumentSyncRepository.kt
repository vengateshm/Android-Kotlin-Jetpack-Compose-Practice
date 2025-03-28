package dev.vengateshm.xml_kotlin.documents

interface DocumentSyncRepository {
    suspend fun syncDocuments()
}