package dev.vengateshm.xml_kotlin.component_based_design.components

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

abstract class HomeScreenComponent<T : ViewModel>(protected val viewModel: T) {
    abstract fun getView(container: ViewGroup, context: Context): View
    abstract fun attachLifecycleOwner(owner: LifecycleOwner)
}