package dev.vengateshm.android_kotlin_compose_practice.manual_di

import android.content.Context

interface CallAppModule {
    val callRepo: CallRepo
    val spamRepo: SpamRepo
}

class CallAppModuleImpl(private val context: Context) : CallAppModule {
    override val callRepo: CallRepo by lazy {
        CallRepoImpl()
    }
    override val spamRepo: SpamRepo by lazy {
        SpamRepoImpl()
    }
}