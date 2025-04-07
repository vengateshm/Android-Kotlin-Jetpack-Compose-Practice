package dev.vengateshm.commonui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.vengateshm.commonui.navigation.NavigationEvent
import dev.vengateshm.commonui.navigation.NavigationLiveData

abstract class BaseFragment : Fragment() {
    protected fun configureNavigationListener(navigationLiveData: NavigationLiveData<NavigationEvent>) {
        navigationLiveData.observe(viewLifecycleOwner) { event ->
            findNavController().navigate(
                event.navId,
                event.argumentsBundle(),
                event.navigationOptions,
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified V : ViewModel> Fragment.obtainViewModel(crossinline viewModelInitializer: () -> V): V {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInitializer() as T
            }
        }
        return ViewModelProvider(this, factory)[V::class.java]
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified V : ViewModel> Fragment.obtainViewModelWithSavedState(
        key: String? = null, defaultArg: Bundle? = null,
        crossinline viewModelInitializer: (SavedStateHandle) -> V,
    ): V {
        val factory = object :
            AbstractSavedStateViewModelFactory(this@BaseFragment, defaultArg) {
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle,
            ): T {
                return viewModelInitializer(handle) as T
            }
        }
        return if (key == null) {
            ViewModelProvider(this, factory)[V::class.java]
        } else {
            ViewModelProvider(this, factory)[key, V::class.java]
        }
    }
}