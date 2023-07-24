package dev.vengateshm.common_lib

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface PreferenceProvider {
    fun prefsDatastore(): DataStore<Preferences>
}