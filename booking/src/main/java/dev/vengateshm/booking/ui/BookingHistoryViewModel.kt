package dev.vengateshm.booking.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.appcore.ServiceResult
import dev.vengateshm.booking.model.Booking
import dev.vengateshm.booking.repository.BookingHistoryRepository
import kotlinx.coroutines.launch

class BookingHistoryViewModel(bookingHistoryRepository: BookingHistoryRepository) : ViewModel() {
    private val _bookingHistory = MutableLiveData<List<Booking>>()
    val bookingHistory: LiveData<List<Booking>> = _bookingHistory

    init {
        viewModelScope.launch {
            bookingHistoryRepository.getBookingHistory().let {
                if (it is ServiceResult.Success) {
                    _bookingHistory.value = it.data
                }
            }
        }
    }
}