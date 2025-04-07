package dev.vengateshm.booking.repository

import android.content.Context
import dev.vengateshm.booking.model.Booking

class BookingHistoryRepositoryImpl(private val context: Context) : BookingHistoryRepository {
    override suspend fun getBookingHistory(): List<Booking> {
        return listOf(
            Booking("900", 1, "Completed", "2025-03-01"),
            Booking("850", 1, "Cancelled", "2025-02-20"),
        )
    }
}