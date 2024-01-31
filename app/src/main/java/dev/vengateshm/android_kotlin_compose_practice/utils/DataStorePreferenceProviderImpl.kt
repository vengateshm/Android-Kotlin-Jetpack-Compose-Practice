package dev.vengateshm.android_kotlin_compose_practice.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dev.vengateshm.appcore.PreferenceProvider

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")

class DataStorePreferenceProviderImpl(private val context: Context) : PreferenceProvider {
    override fun prefsDatastore(): DataStore<Preferences> = context.dataStore
}
