package dev.vengateshm.xml_kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dev.vengateshm.xml_kotlin.documents.DocumentSyncRepositoryImpl
import dev.vengateshm.xml_kotlin.lifecycle.lifecycleobserver.LastSyncDate
import kotlinx.coroutines.launch
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
class XMLKotlinActivityViewModel : ViewModel() {
    private val documentSyncRepository = DocumentSyncRepositoryImpl()

    val lastSyncTimeLiveData = LastSyncDate.getLastSyncedDateLiveData()
        .map { dateAndTime ->
            dateAndTime?.let {
                val lastSyncTimeInMillis = it.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000
                println("LastSyncTimeInMillis: $lastSyncTimeInMillis")
                lastSyncTimeInMillis
            }
        }

    init {
        viewModelScope.launch {
            documentSyncRepository.syncDocuments()
        }
    }
}