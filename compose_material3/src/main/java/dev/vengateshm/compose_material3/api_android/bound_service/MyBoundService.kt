package dev.vengateshm.compose_material3.api_android.bound_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyBoundService : Service() {

    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService() = this@MyBoundService
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return true
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun translate(text: String, onResult: (String) -> Unit) {
        onResult("Translated: ${text.uppercase()}")
    }
}