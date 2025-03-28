package dev.vengateshm.xml_kotlin.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import dev.vengateshm.xml_kotlin.lifecycle.lifecycleobserver.LastSyncDate
import kotlinx.coroutines.CoroutineScope

class HomeScreenInitializer {
    @RequiresApi(Build.VERSION_CODES.O)
    fun initialize(
        lifecycle: Lifecycle,
        context: Context,
        coroutineScope: CoroutineScope,
    ) {
        LastSyncDate.init(context.applicationContext)
        lifecycle.addObserver(LastSyncDate)
    }
}