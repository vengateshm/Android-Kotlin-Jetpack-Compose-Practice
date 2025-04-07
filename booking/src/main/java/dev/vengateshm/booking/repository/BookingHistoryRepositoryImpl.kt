package dev.vengateshm.booking.repository

import android.content.Context
import dev.vengateshm.appcore.ServiceException
import dev.vengateshm.appcore.ServiceResult
import dev.vengateshm.appcore.utility.asStringWrapper
import dev.vengateshm.booking.model.Booking

class BookingHistoryRepositoryImpl(private val context: Context) : BookingHistoryRepository {
    override suspend fun getBookingHistory(): ServiceResult<List<Booking>> {
        return try {
            ServiceResult.Success(
                listOf(
                    Booking("900", 1, "Completed", "2025-03-01"),
                    Booking("850", 1, "Cancelled", "2025-02-20"),
                ),
            )
        } catch (e: Exception) {
            ServiceResult.Error(ServiceException(null, "Something went wrong".asStringWrapper()))
        }
    }
}