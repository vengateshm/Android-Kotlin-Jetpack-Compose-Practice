package dev.vengateshm.booking.ui

import android.os.Bundle
import dev.vengateshm.appcore.utility.getParcelableSafely
import dev.vengateshm.booking.R
import dev.vengateshm.booking.model.Booking
import dev.vengateshm.commonui.navigation.NavigationArguments
import dev.vengateshm.commonui.navigation.NavigationDestination

class BookingDestination(booking: Booking) : NavigationDestination(
    R.id.booking_screen_main_navigation,
    NavigationArguments.create {
        putParcelable(BOOKING_DATA_KEY, booking)
    },
) {
    companion object {
        private const val BOOKING_DATA_KEY = "bookingDataKey"

        fun getBookingData(arguments: Bundle?): Booking? =
            arguments?.getParcelableSafely(BOOKING_DATA_KEY, Booking::class.java)
    }
}