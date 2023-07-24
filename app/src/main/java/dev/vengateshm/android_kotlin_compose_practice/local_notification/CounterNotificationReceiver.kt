package dev.vengateshm.android_kotlin_compose_practice.local_notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CounterNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (action == "increment") {
            // Increment the counter value.
            Counter.counter++
        } else if (action == "decrement") {
            // Decrement the counter value.
            Counter.counter--
        }
        val counterNotificationService = CounterNotificationService(context!!)
        counterNotificationService.showNotification(Counter.counter)
    }
}