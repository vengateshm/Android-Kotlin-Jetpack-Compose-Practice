package dev.vengateshm.xml_kotlin.navigation

import android.os.Bundle
import android.view.View

class FragmentB1 : FragmentB() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentB1Data("Fragment B1 data")
    }
}