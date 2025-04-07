package dev.vengateshm.booking.repository

import android.content.Context
import dev.vengateshm.booking.model.Booking

interface BookingHistoryRepository {
    suspend fun getBookingHistory(): List<Booking>

    companion object {
        fun create(context: Context): BookingHistoryRepository {
            return BookingHistoryRepositoryImpl(context)
        }
    }
}