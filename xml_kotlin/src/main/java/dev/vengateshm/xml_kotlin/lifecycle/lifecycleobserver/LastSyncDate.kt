package dev.vengateshm.xml_kotlin.lifecycle.lifecycleobserver

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import androidx.core.content.edit
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vengateshm.xml_kotlin.utils.DATE_TIME_FORMAT_UNIVERSAL
import dev.vengateshm.xml_kotlin.utils.convertStringToDateTimeUS
import dev.vengateshm.xml_kotlin.utils.format
import java.time.LocalDateTime

object LastSyncDate : DefaultLifecycleObserver {
    private const val LAST_SYNC_DATE_PREFS_NAME = "dev.vengateshm.last_sync_date_prefs"
    private const val LAST_SYNC_DATE = "dev.vengateshm.last_sync_date"

    private var sharedPreferences: SharedPreferences? = null
    private var lastSyncDate: LocalDateTime? = null
    private var lastSyncedDateLiveData = MutableLiveData<LocalDateTime?>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun init(applicationContext: Context) {
        sharedPreferences = applicationContext.getSharedPreferences(
            LAST_SYNC_DATE_PREFS_NAME,
            Context.MODE_PRIVATE,
        )
        val lastSyncDate = sharedPreferences?.getString(LAST_SYNC_DATE, "")
        setLastSyncedDate(
            lastSyncDate?.convertStringToDateTimeUS(
                DATE_TIME_FORMAT_UNIVERSAL,
            ),
        )
    }

    @VisibleForTesting
    fun clear() {
        lastSyncDate = null
        lastSyncedDateLiveData.value = null
//        lastSyncedDateLiveData.postValue(null)
        sharedPreferences = null
    }

    fun getLastSyncedDate(): LocalDateTime? {
        return lastSyncDate
    }

    fun setLastSyncedDate(date: LocalDateTime?) {
        lastSyncDate = date
        lastSyncedDateLiveData.postValue(date)
    }

    fun getLastSyncedDateLiveData(): LiveData<LocalDateTime?> {
        return lastSyncedDateLiveData
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        saveLastSyncDateTime()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveLastSyncDateTime() {
        sharedPreferences?.edit {
            lastSyncDate?.let {
                putString(
                    LAST_SYNC_DATE,
                    it.format(DATE_TIME_FORMAT_UNIVERSAL).toString(),
                )
            }
        }
    }
}