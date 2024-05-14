package dev.vengateshm.xml_kotlin.navigation

import android.os.Bundle
import android.view.View

class FragmentB2 : FragmentB() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentB2Data("Fragment B2 data")
    }
}