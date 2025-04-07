package dev.vengateshm.xml_kotlin.multi_module

import androidx.lifecycle.ViewModel
import dev.vengateshm.appcore.SingleEmissionMutableLiveData
import dev.vengateshm.appcore.comm.CommData
import dev.vengateshm.appcore.comm.CommPath
import dev.vengateshm.appcore.comm.CommType
import dev.vengateshm.appcore.model.booking.BookingConfig
import dev.vengateshm.booking.BookingRequestCode

class MultiModuleMainLandingViewModel : ViewModel() {

    private val _externalComm = SingleEmissionMutableLiveData<CommData>()
    val externalComm: SingleEmissionMutableLiveData<CommData> = _externalComm

    fun navigateToBooking() {
        // Set value to get data / navigate
        _externalComm.value = CommData(
            originatePath = CommPath.MAIN,
            destinationPath = CommPath.BOOKING,
            requestCode = BookingRequestCode.BOOKING.name,
            requestType = CommType.GET_DATA,
            data = BookingConfig(bookingId = "1001"),
        )
    }

    // Handle result of GET_DATA
    fun handleCommResult(data: CommData?) {
        data?.run {
            when (requestCode) {
                BookingRequestCode.BOOKING.name -> {
                    _externalComm.value = CommData(
                        originatePath = CommPath.MAIN,
                        destinationPath = CommPath.BOOKING,
                        requestCode = BookingRequestCode.BOOKING.name,
                        requestType = CommType.NAVIGATION,
                        data = this.data,
                    )
                }
            }
        }
    }
}