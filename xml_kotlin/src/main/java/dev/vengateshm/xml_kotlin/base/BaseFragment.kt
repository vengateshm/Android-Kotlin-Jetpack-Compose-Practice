package dev.vengateshm.xml_kotlin.base

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.vengateshm.xml_kotlin.utils.navigation.NavigationEvent
import dev.vengateshm.xml_kotlin.utils.navigation.NavigationLiveData

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
}