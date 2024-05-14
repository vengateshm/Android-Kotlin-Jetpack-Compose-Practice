package dev.vengateshm.xml_kotlin.navigation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.vengateshm.xml_kotlin.R

class FragmentA : Fragment(R.layout.fragment_a) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            findViewById<Button>(R.id.btnFB1).setOnClickListener {
//                findNavController().navigate(R.id.action_fragmentA_to_fragmentB1)
                findNavController().navigate(R.id.action_fragmentA_to_fragmentB, bundleOf(
                    "from" to "Data from fragment A"
                ))
            }
        }
    }
}