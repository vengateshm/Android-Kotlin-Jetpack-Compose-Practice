package dev.vengateshm.android_kotlin_compose_practice

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import dev.vengateshm.android_kotlin_compose_practice.preference_datastore_test.MyDatastore
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MyDatastoreTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile("my_datastore")
        }
    )
    private val sut = MyDatastore(dataStore)

    @Test
    fun test_save_read_string_value() = runTest {
        sut.saveStringToDataStore("brand", "Relish")

        val saved = sut.readStringFromDataStore("brand")
        assertEquals("Relish", saved)
    }
}