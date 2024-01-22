package dev.vengateshm.android_kotlin_compose_practice.delegation

import android.content.Intent

interface DeepLinkHandler {
    fun handleDeepLink(intent: Intent?)
}

class DeepLinkHandlerImpl : DeepLinkHandler {
    override fun handleDeepLink(intent: Intent?) {
    }
}
