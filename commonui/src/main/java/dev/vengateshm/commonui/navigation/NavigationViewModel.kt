package dev.vengateshm.commonui.navigation

import androidx.lifecycle.ViewModel

open class NavigationViewModel : ViewModel() {
    val navigationLiveData = NavigationLiveData<NavigationEvent>()

    fun navigateTo(destination: NavigationDestination) {
        navigationLiveData.latestValue = destination.buildEvent()
    }
}