package dev.vengateshm.xml_kotlin.component_based_design

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.component_based_design.components.ClimateComponent
import dev.vengateshm.xml_kotlin.component_based_design.components.EnergyComponent
import dev.vengateshm.xml_kotlin.component_based_design.components.QuickActionsComponent
import dev.vengateshm.xml_kotlin.component_based_design.components.SecurityComponent
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.ClimateViewModel
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.EnergyViewModel
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.QuickActionsViewModel
import dev.vengateshm.xml_kotlin.component_based_design.viewmodels.SecurityViewModel

class HomeScreenFragment : Fragment() {

    private val securityViewModel: SecurityViewModel by viewModels()
    private val climateViewModel: ClimateViewModel by viewModels()
    private val energyViewModel: EnergyViewModel by viewModels()
    private val quickActionsViewModel: QuickActionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val componentsLayout1 = view.findViewById<LinearLayout>(R.id.componentsLayout1)
        val componentsLayout2 = view.findViewById<LinearLayout>(R.id.componentsLayout2)

        SecurityComponent(securityViewModel).apply {
            componentsLayout1.addView(getView(componentsLayout1, requireContext()))
            attachLifecycleOwner(viewLifecycleOwner)
        }

        ClimateComponent(climateViewModel).apply {
            componentsLayout1.addView(getView(componentsLayout1, requireContext()))
            attachLifecycleOwner(viewLifecycleOwner)
        }

        EnergyComponent(energyViewModel).apply {
            componentsLayout2.addView(getView(componentsLayout2, requireContext()))
            attachLifecycleOwner(viewLifecycleOwner)
        }

        QuickActionsComponent(quickActionsViewModel).apply {
            componentsLayout2.addView(getView(componentsLayout2, requireContext()))
            attachLifecycleOwner(viewLifecycleOwner)
        }
    }
}