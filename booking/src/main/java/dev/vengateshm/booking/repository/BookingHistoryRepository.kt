package dev.vengateshm.booking.repository

import android.content.Context
import dev.vengateshm.appcore.ServiceResult
import dev.vengateshm.booking.model.Booking

interface BookingHistoryRepository {
    suspend fun getBookingHistory(): ServiceResult<List<Booking>>

    companion object {
        fun create(context: Context): BookingHistoryRepository {
            return BookingHistoryRepositoryImpl(context)
        }
    }
}