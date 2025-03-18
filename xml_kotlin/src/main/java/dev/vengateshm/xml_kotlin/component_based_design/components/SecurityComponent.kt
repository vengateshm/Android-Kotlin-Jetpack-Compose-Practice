package dev.vengateshm.xml_kotlin.component_based_design.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.SecurityViewModel

class SecurityComponent(viewModel: SecurityViewModel) :
    HomeScreenComponent<SecurityViewModel>(viewModel) {

    private lateinit var view: View

    override fun getView(container: ViewGroup, context: Context): View {
        view = LayoutInflater.from(context).inflate(R.layout.component_security, container, false)
        return view
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.securityStatus.observe(owner) {
            view.findViewById<TextView>(R.id.securityStatus).text = it
        }
        viewModel.doorStatus.observe(owner) {
            view.findViewById<TextView>(R.id.doorStatus).text = it
        }
        viewModel.windowStatus.observe(owner) {
            view.findViewById<TextView>(R.id.windowStatus).text = it
        }
        viewModel.motionStatus.observe(owner) {
            view.findViewById<TextView>(R.id.motionStatus).text = it
        }
    }
}