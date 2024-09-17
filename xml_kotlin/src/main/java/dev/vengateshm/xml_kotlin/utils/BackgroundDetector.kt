package dev.vengateshm.xml_kotlin.utils

import android.util.Log

class BackgroundDetector {
    interface Listener {
        fun onBackground()
        fun onForeground()
    }

    private var startedActivitiesNum = 0
    private val listeners = mutableListOf<Listener>()

    fun activityStarted() {
        startedActivitiesNum++
        if (startedActivitiesNum == 1) {
            Log.d("BackgroundDetector", "onForeground")
            listeners.map { it.onForeground() }
        }
    }

    fun activityStopped() {
        startedActivitiesNum--
        if (startedActivitiesNum == 0) {
            Log.d("BackgroundDetector", "onBackground")
            listeners.map { it.onBackground() }
        }
    }

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }
}