package dev.vengateshm.xml_kotlin.component_based_design.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.ClimateViewModel

class ClimateComponent(viewModel: ClimateViewModel) :
    HomeScreenComponent<ClimateViewModel>(viewModel) {

    private lateinit var view: View

    override fun getView(container: ViewGroup, context: Context): View {
        view = LayoutInflater.from(context).inflate(R.layout.component_climate, container, false)
        return view
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.temperature.observe(owner) {
            view.findViewById<TextView>(R.id.temperature).text = it
        }
        viewModel.humidity.observe(owner) {
            view.findViewById<TextView>(R.id.humidity).text = it
        }
    }
}