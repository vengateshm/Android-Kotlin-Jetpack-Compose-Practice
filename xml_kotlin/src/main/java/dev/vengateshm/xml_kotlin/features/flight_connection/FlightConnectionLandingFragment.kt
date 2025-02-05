package dev.vengateshm.xml_kotlin.features.flight_connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.vengateshm.xml_kotlin.base.BaseFragment
import dev.vengateshm.xml_kotlin.databinding.FragmentFlightConnectionLandingBinding

class FlightConnectionLandingFragment : BaseFragment() {

    private var _binding: FragmentFlightConnectionLandingBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFlightConnectionLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}