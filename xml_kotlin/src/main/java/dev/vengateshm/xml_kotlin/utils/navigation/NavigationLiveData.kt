package dev.vengateshm.xml_kotlin.utils.navigation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

//import androidx.lifecycle.OnLifecycleEvent

class NavigationLiveData<T : Any> : LifecycleEventObserver {
    private var liveData = MutableLiveData<T>()
    private var lifecycleOwner: LifecycleOwner? = null

    var latestValue: T?
        get() = liveData.value
        set(value) {
            liveData.value = value
        }

    /*@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycleOwner?.let {
            lifecycleOwner = null
            liveData = MutableLiveData()
            it.lifecycle.removeObserver(this)
        }
    }*/

    fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        check(lifecycleOwner == null) { "Observer already attached" }

        lifecycleOwner = owner.also {
            it.lifecycle.addObserver(this)
        }

        liveData.observe(owner, observer)
    }

    override fun onStateChanged(
        source: LifecycleOwner,
        event: Lifecycle.Event,
    ) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("NavigationLiveData", "ON_CREATE")
            }

            Lifecycle.Event.ON_START -> {
                Log.d("NavigationLiveData", "ON_START")
            }

            Lifecycle.Event.ON_RESUME -> {
                Log.d("NavigationLiveData", "ON_RESUME")
            }

            Lifecycle.Event.ON_PAUSE -> {
                Log.d("NavigationLiveData", "ON_PAUSE")
            }

            Lifecycle.Event.ON_STOP -> {
                Log.d("NavigationLiveData", "ON_STOP")
            }

            Lifecycle.Event.ON_DESTROY -> {
                lifecycleOwner?.let {
                    lifecycleOwner = null
                    liveData = MutableLiveData()
                    it.lifecycle.removeObserver(this)
                }
            }

            Lifecycle.Event.ON_ANY -> {
                Log.d("NavigationLiveData", "ON_ANY")
            }
        }
    }
}