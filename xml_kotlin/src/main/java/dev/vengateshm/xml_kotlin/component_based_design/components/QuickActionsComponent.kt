package dev.vengateshm.xml_kotlin.component_based_design.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.QuickActionsViewModel

class QuickActionsComponent(viewModel: QuickActionsViewModel) :
    HomeScreenComponent<QuickActionsViewModel>(viewModel) {

    private lateinit var view: View

    override fun getView(container: ViewGroup, context: Context): View {
        view =
            LayoutInflater.from(context).inflate(R.layout.component_quick_actions, container, false)
        return view
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.lightsStatus.observe(owner) {
            view.findViewById<TextView>(R.id.lightsStatus).text = it
        }
        viewModel.fanStatus.observe(owner) {
            view.findViewById<TextView>(R.id.fanStatus).text = it
        }
        viewModel.plugStatus.observe(owner) {
            view.findViewById<TextView>(R.id.plugStatus).text = it
        }
    }
}