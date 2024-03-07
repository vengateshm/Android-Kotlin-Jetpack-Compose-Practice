package dev.vengateshm.android_kotlin_compose_practice.res_strings_cache_in_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

data class ResStrings(
    val firstNameLabel: String,
    val lastNameLabel: String
)

class ResStringsCacheInViewModel(
    private val resStrings: ResStrings
) : ViewModel() {
    fun getFirstNameLabel(): String {
        return resStrings.firstNameLabel
    }

    fun getLastNameLabel(): String {
        return resStrings.lastNameLabel
    }
}

class ResStringsCacheInViewModelProvider(private val resStrings: ResStrings) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResStringsCacheInViewModel::class.java)) {
            Log.d("ResStringCache", "Viewmodel Initialized")
            return ResStringsCacheInViewModel(resStrings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}