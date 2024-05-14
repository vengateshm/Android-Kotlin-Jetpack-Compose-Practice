package dev.vengateshm.xml_kotlin.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.vengateshm.xml_kotlin.R

open class FragmentB : Fragment(R.layout.fragment_b) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            Log.d("BUNDLE DATA", getString("from").orEmpty())
        }

        view.run {
            findViewById<Button>(R.id.btnFB2).setOnClickListener {
//                findNavController().navigate(R.id.action_fragmentB1_to_fragmentB2)
                findNavController().navigate(
                    R.id.action_fragmentB_self, bundleOf(
                        "from" to "Data from fragment B"
                    )
                )
            }
            findViewById<Button>(R.id.btnBack).setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    fun setFragmentB1Data(value: String) {
        view?.run {
            findViewById<TextView>(R.id.tvText).text = value
        }
    }

    fun setFragmentB2Data(value: String) {
        view?.run {
            findViewById<TextView>(R.id.tvText).text = value
        }
    }
}