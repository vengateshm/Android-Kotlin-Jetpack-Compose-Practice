package dev.vengateshm.xml_kotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.content.edit
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vengateshm.xml_kotlin.lifecycle.lifecycleobserver.LastSyncDate
import dev.vengateshm.xml_kotlin.utils.DATE_TIME_FORMAT_UNIVERSAL
import dev.vengateshm.xml_kotlin.utils.convertStringToDateTimeUS
import dev.vengateshm.xml_kotlin.utils.format
import dev.vengateshm.xml_kotlin.utils.testObserver
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class LastSyncDateTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sharedPreferences: SharedPreferences
    private val lastSyncDate = LastSyncDate

    @Before
    fun setUp() {
        lastSyncDate.init(ApplicationProvider.getApplicationContext<Context>())
        sharedPreferences = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences(LAST_SYNC_DATE_PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Test
    fun getLastSyncedDateLiveData_should_return_null_when_called_for_first_time() {
        val observer = lastSyncDate.getLastSyncedDateLiveData().testObserver()
        assert(observer.observedValues.isNotEmpty())
        assertThat(observer.observedValues[0], nullValue())
    }

    @Test
    fun getLastSyncedDateLiveData_should_not_return_value_from_shared_prefs_when_set_manually() {
        val localDateTime = LocalDateTime.of(2019, 11, 2, 6, 11, 33)
        val localDateTime2 = LocalDateTime.of(2018, 10, 1, 5, 10, 30)
        sharedPreferences.edit {
            putString(
                LAST_SYNC_DATE,
                localDateTime.format(DATE_TIME_FORMAT_UNIVERSAL),
            ).commit()
        }
        lastSyncDate.setLastSyncedDate(localDateTime2)
        val observer = lastSyncDate.getLastSyncedDateLiveData().testObserver()
        assertThat(observer.observedValues[0], `is`(localDateTime2))
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun getLastSyncedDate_should_return_value_from_shared_prefs_when_called_for_the_first_time() {
        val localDateTime = LocalDateTime.of(2019, 11, 2, 6, 11, 33)
        sharedPreferences.edit {
            putString(
                LAST_SYNC_DATE,
                localDateTime.format(DATE_TIME_FORMAT_UNIVERSAL),
            ).commit()
        }
        lastSyncDate.init(ApplicationProvider.getApplicationContext<Context>())
        assertThat(lastSyncDate.getLastSyncedDate(), `is`(localDateTime))
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun getLastSyncedDate_should_not_return_from_shared_preference_when_already_set() {
        val localDateTime = LocalDateTime.of(2019, 11, 2, 6, 11, 33)
        val localDateTime2 = LocalDateTime.of(2018, 10, 1, 5, 10, 30)
        sharedPreferences.edit {
            putString(
                LAST_SYNC_DATE,
                localDateTime.format(DATE_TIME_FORMAT_UNIVERSAL),
            ).commit()
        }
        lastSyncDate.setLastSyncedDate(localDateTime2)
        assertThat(lastSyncDate.getLastSyncedDate(), `is`(localDateTime2))
        assert(lastSyncDate.getLastSyncedDate() != localDateTime)
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun getLastSyncedDateLiveData_should_return_value_set_in_the_local_variable() {
        val localDateTime = LocalDateTime.of(2019, 11, 2, 6, 11, 33)
        lastSyncDate.setLastSyncedDate(localDateTime)
        assertThat(lastSyncDate.getLastSyncedDateLiveData().value, `is`(localDateTime))
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun saveLastSyncedTime_should_save_time_in_shared_preference() {
        val localDateTime = LocalDateTime.of(2019, 11, 2, 6, 11, 33)
        lastSyncDate.setLastSyncedDate(localDateTime)
        lastSyncDate.saveLastSyncDateTime()
        assertThat(
            sharedPreferences.getString(LAST_SYNC_DATE, "")
                ?.convertStringToDateTimeUS(DATE_TIME_FORMAT_UNIVERSAL),
            `is`(localDateTime),
        )
        sharedPreferences.edit().clear().commit()
    }

    @After
    fun cleanUp() {
        lastSyncDate.clear()
        sharedPreferences.edit().clear().commit()
    }

    companion object {
        private const val LAST_SYNC_DATE_PREFS_NAME = "dev.vengateshm.last_sync_date_prefs"
        private const val LAST_SYNC_DATE = "dev.vengateshm.last_sync_date"
    }
}