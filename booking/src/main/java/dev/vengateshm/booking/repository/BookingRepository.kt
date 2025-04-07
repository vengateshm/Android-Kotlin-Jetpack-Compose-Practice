package dev.vengateshm.booking.repository

import android.content.Context
import dev.vengateshm.booking.model.Booking

interface BookingRepository {
    suspend fun getBooking(bookingId: String): Booking?

    companion object {
        fun create(context: Context): BookingRepository {
            return BookingRepositoryImpl(context)
        }
    }
}