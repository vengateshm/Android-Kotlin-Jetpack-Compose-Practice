package dev.vengateshm.xml_kotlin.component_based_design.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.EnergyViewModel

class EnergyComponent(viewModel: EnergyViewModel) :
    HomeScreenComponent<EnergyViewModel>(viewModel) {

    private lateinit var view: View

    override fun getView(container: ViewGroup, context: Context): View {
        view = LayoutInflater.from(context).inflate(R.layout.component_energy, container, false)
        return view
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.currentUsage.observe(owner) {
            view.findViewById<TextView>(R.id.currentUsage).text = it
        }
        viewModel.dailyUsage.observe(owner) {
            view.findViewById<TextView>(R.id.dailyUsage).text = it
        }
    }
}