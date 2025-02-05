package dev.vengateshm.xml_kotlin.features.flight_connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.vengateshm.xml_kotlin.base.BaseFragment
import dev.vengateshm.xml_kotlin.databinding.FragmentFlightConnectionGateTimeBinding
import dev.vengateshm.xml_kotlin.utils.getViewModel

class FlightConnectionGateTimeFragment : BaseFragment() {

    private var _binding: FragmentFlightConnectionGateTimeBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by lazy {
        getViewModel {
            FlightConnectionGateTimeFragmentViewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFlightConnectionGateTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureNavigationListener(viewModel.navigationLiveData)
        configureUIListeners(view)
        observeViewModel(view)
    }

    private fun configureUIListeners(view: View) {
        binding.flightConnectionGateTimeViewComfortable.infoIconClickListener = {
            viewModel.navigateToConnectionTimeInfo()
        }
    }

    private fun observeViewModel(view: View) {
        viewModel.apply {
            flightConnectionGateTimeDataComfortable.observe(viewLifecycleOwner) {
                binding.flightConnectionGateTimeViewComfortable.setData(it)
            }
            flightConnectionGateTimeDataTight.observe(viewLifecycleOwner) {
                binding.flightConnectionGateTimeViewTight.setData(it)
            }
            flightConnectionGateTimeDataNoTime.observe(viewLifecycleOwner) {
                binding.flightConnectionGateTimeViewNoTime.setData(it)
            }
            flightConnectionGateTimeDataNoWalkTime.observe(viewLifecycleOwner) {
                binding.flightConnectionGateTimeViewNoWalkTime.setData(it)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}