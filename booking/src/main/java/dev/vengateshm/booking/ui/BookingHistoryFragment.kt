package dev.vengateshm.booking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.vengateshm.booking.R
import dev.vengateshm.booking.repository.BookingHistoryRepository
import dev.vengateshm.commonui.base.BaseFragment

class BookingHistoryFragment : BaseFragment() {
    private val viewModel: BookingHistoryViewModel by lazy {
        obtainViewModel {
            BookingHistoryViewModel(
                BookingHistoryRepository.create(requireContext()),
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_booking_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(view)
    }

    private fun observeViewModel(view: View) {
        viewModel.apply {
            bookingHistory.observe(viewLifecycleOwner) {
                view.findViewById<TextView>(R.id.bookingHistoryText).text = it.toString()
            }
        }
    }
}