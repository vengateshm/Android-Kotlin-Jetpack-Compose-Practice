package dev.vengateshm.xml_kotlin.documents

import android.os.Build
import androidx.annotation.RequiresApi
import dev.vengateshm.xml_kotlin.lifecycle.lifecycleobserver.LastSyncDate
import kotlinx.coroutines.delay
import java.time.LocalDateTime

class DocumentSyncRepositoryImpl : DocumentSyncRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun syncDocuments() {
        delay(5000L)
        LastSyncDate.setLastSyncedDate(LocalDateTime.now())
    }
}