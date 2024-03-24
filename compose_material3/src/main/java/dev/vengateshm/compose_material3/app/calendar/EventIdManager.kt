package dev.vengateshm.compose_material3.app.calendar

import android.content.Context
import androidx.preference.PreferenceManager
class EventIdManager(context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val key = "event_id_counter"

    fun getNextEventId(): Int {
        val currentCounter = preferences.getInt(key, 100)
        val nextId = currentCounter + 1
        preferences.edit().putInt(key, nextId).apply() // Atomically save and apply
        return nextId
    }
}