package dev.vengateshm.booking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.vengateshm.booking.R
import dev.vengateshm.commonui.base.BaseFragment

class BookingFragment : BaseFragment() {

    private val viewModel: BookingViewModel by lazy {
        obtainViewModel {
            BookingViewModel(
                bookingData = arguments?.let {
                    BookingDestination.getBookingData(it)
                },
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(view)
    }

    private fun observeViewModel(view: View) {
        viewModel.apply {
            booking.observe(viewLifecycleOwner) {
                it ?: return@observe
                view.findViewById<TextView>(R.id.currentBookingText).apply {
                    text = it.toString()
                }
            }
        }
    }
}