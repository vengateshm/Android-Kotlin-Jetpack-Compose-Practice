package dev.vengateshm.booking.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vengateshm.booking.model.Booking

class BookingViewModel(
    private val bookingData: Booking?,
) : ViewModel() {
    private val _booking = MutableLiveData<Booking?>()
    val booking: LiveData<Booking?> = _booking

    init {
        _booking.value = bookingData
    }
}