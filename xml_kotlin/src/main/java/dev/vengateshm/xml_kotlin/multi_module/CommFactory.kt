package dev.vengateshm.xml_kotlin.multi_module

import android.content.Context
import dev.vengateshm.appcore.comm.CommData
import dev.vengateshm.appcore.comm.CommPath
import dev.vengateshm.appcore.comm.CommProcessor
import dev.vengateshm.booking.comm.BookingFacade
import dev.vengateshm.home.comm.HomeFacade

class CommFactory(private val context: Context) {
    fun getComm(data: CommData): CommProcessor {
        return when (data.destinationPath) {
            CommPath.HOME -> HomeFacade(context)
            CommPath.BOOKING -> BookingFacade(context)
            CommPath.MAIN -> MainAppFacade(context)
        }
    }
}