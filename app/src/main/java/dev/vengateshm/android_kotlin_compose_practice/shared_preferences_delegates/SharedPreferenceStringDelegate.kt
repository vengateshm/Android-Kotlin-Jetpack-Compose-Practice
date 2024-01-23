package dev.vengateshm.android_kotlin_compose_practice.shared_preferences_delegates

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceStringDelegate(
    private val context: Context,
    private val key: String,
    private val defaultValue: String = ""
) : ReadWriteProperty<Any?, String> {

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return preferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        preferences.edit().putString(key, value).apply()
    }
}

fun Context.stringPrefs(key: String) = SharedPreferenceStringDelegate(
    context = this,
    key = key,
    defaultValue = ""
)