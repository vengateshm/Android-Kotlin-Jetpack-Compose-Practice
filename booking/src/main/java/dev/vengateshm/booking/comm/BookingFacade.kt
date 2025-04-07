package dev.vengateshm.booking.comm

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import dev.vengateshm.appcore.comm.CommData
import dev.vengateshm.appcore.comm.CommPath
import dev.vengateshm.appcore.comm.CommProcessor
import dev.vengateshm.appcore.comm.CommType
import dev.vengateshm.appcore.model.booking.BookingConfig
import dev.vengateshm.booking.BookingRequestCode
import dev.vengateshm.booking.model.Booking
import dev.vengateshm.booking.repository.BookingHistoryRepository
import dev.vengateshm.booking.repository.BookingRepository
import dev.vengateshm.booking.ui.BookingDestination

class BookingFacade(private val context: Context) : CommProcessor {

    private val bookingRepository = BookingRepository.create(context)
    private val bookingHistoryRepository = BookingHistoryRepository.create(context)

    override fun doNavigation(
        navController: NavController,
        data: CommData,
    ) {
        val code = data.requestCode
        if (data.originatePath == CommPath.MAIN && code == BookingRequestCode.BOOKING.name) {
            (data.data as? Booking)?.let {
                val navEvent = BookingDestination(booking = it).buildEvent()
                Log.d(
                    "NavDebug",
                    "Current destination: ${navController.currentDestination?.label} (${navController.currentDestination?.id})",
                )
                navController.navigate(navEvent.navId, navEvent.argumentsBundle())
            }
        }
    }

    override suspend fun processRequest(data: CommData): CommData? {
        if (data.destinationPath != CommPath.BOOKING) return null
        if (data.requestType != CommType.GET_DATA) return null

        val code = data.requestCode
        if (data.originatePath == CommPath.MAIN && code == BookingRequestCode.BOOKING.name) {
            val bookingConfig = data.data as? BookingConfig
            bookingConfig ?: return null
            return CommData(
                originatePath = CommPath.BOOKING,
                destinationPath = CommPath.MAIN,
                requestCode = BookingRequestCode.BOOKING.name,
                requestType = CommType.GET_DATA,
                data = bookingRepository.getBooking(bookingConfig.bookingId),
            )
        }
        return null
    }
}