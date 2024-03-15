package dev.vengateshm.android_kotlin_compose_practice.manual_di

import androidx.lifecycle.ViewModel

class SpamViewModel(
    private val spamRepo: SpamRepo
) : ViewModel() {

    fun getSpamList() {

    }
}