package dev.vengateshm.xml_kotlin.multi_module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dev.vengateshm.appcore.comm.CommViewModel
import dev.vengateshm.xml_kotlin.R

class MultiModuleMainLandingFragment : Fragment() {
    private val mainViewModel: MultiModuleMainLandingViewModel by viewModels()
    private val commViewModel: CommViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_multi_module_main_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupObservers()
    }

    private fun setupViews(view: View) {
        view.findViewById<Button>(R.id.btnHome).apply {
            setOnClickListener {

            }
        }
        view.findViewById<Button>(R.id.btnBooking).apply {
            setOnClickListener {
                mainViewModel.navigateToBooking()
            }
        }
        view.findViewById<Button>(R.id.btnBookingHistory).apply {
            setOnClickListener {
                mainViewModel.navigateToBookingHistory()
            }
        }
    }

    private fun setupObservers() {
        commViewModel.commResult.observe(viewLifecycleOwner) {
            mainViewModel.handleCommResult(it)
        }

        mainViewModel.externalComm.observe(viewLifecycleOwner) {
            // Listen here to get data / navigate
            // Post this to commViewModel to handle
            commViewModel.requestCommEvent(it)
        }
    }
}