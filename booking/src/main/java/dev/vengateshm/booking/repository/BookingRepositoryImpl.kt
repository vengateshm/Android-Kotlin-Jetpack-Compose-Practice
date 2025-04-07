package dev.vengateshm.booking.repository

import android.content.Context
import dev.vengateshm.booking.model.Booking

class BookingRepositoryImpl(private val context: Context) : BookingRepository {
    override suspend fun getBooking(bookingId: String): Booking? {
        return sampleBookings.find { it.id == bookingId }
    }
}

val sampleBookings = listOf(
    Booking(id = "1001", userId = 1, status = "Confirmed", date = "2025-04-01"),
    Booking(id = "1002", userId = 1, status = "Completed", date = "2025-03-28"),
    Booking(id = "1003", userId = 2, status = "Cancelled", date = "2025-03-20"),
    Booking(id = "1004", userId = 3, status = "Pending", date = "2025-04-05"),
    Booking(id = "1005", userId = 2, status = "Confirmed", date = "2025-04-02"),
)
