package dev.vengateshm.xml_kotlin.utils.navigation

import androidx.annotation.MainThread
import androidx.collection.ArraySet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleEmissionMutableLiveData<T> : MutableLiveData<T>() {

    private val observers = ArraySet<ObserverWrapper<in T>>()

    @MainThread
    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<in T>,
    ) {
        ObserverWrapper(observer).also {
            observers.add(it)
            super.observe(owner, it)
        }
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        if (observers.remove<Observer<in T>>(observer)) {
            super.removeObserver(observer)
            return
        }
        val iterator = observers.iterator()
        while (iterator.hasNext()) {
            val wrapper = iterator.next()
            if (wrapper.observer == observer) {
                iterator.remove()
                super.removeObserver(wrapper)
                break
            }
        }
    }

    @MainThread
    override fun setValue(value: T?) {
        observers.forEach { it.setHasNewValue() }
        super.setValue(value)
    }

    private class ObserverWrapper<T>(val observer: Observer<T>) : Observer<T> {
        private var hasNewValue = false

        override fun onChanged(value: T) {
            if (hasNewValue) {
                hasNewValue = false
                observer.onChanged(value)
            }
        }

        fun setHasNewValue() {
            hasNewValue = true
        }
    }
}