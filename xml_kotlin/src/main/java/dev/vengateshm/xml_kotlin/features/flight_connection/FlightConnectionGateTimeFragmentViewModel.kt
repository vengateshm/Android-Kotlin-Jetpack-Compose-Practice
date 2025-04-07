package dev.vengateshm.xml_kotlin.features.flight_connection

import androidx.lifecycle.MutableLiveData
import dev.vengateshm.commonui.navigation.NavigationViewModel
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.custom_views.flight_connection.ConundrumIconType
import dev.vengateshm.xml_kotlin.custom_views.flight_connection.FlightConnectionGateTimeData
import dev.vengateshm.xml_kotlin.custom_views.time_block.TimeBlockData
import dev.vengateshm.xml_kotlin.custom_views.time_block.TimeState

class FlightConnectionGateTimeFragmentViewModel : NavigationViewModel() {

    private val _flightConnectionGateTimeDataComfortable =
        MutableLiveData<FlightConnectionGateTimeData>()
    val flightConnectionGateTimeDataComfortable = _flightConnectionGateTimeDataComfortable

    private val _flightConnectionGateTimeDataTight = MutableLiveData<FlightConnectionGateTimeData>()
    val flightConnectionGateTimeDataTight = _flightConnectionGateTimeDataTight

    private val _flightConnectionGateTimeDataNoTime =
        MutableLiveData<FlightConnectionGateTimeData>()
    val flightConnectionGateTimeDataNoTime = _flightConnectionGateTimeDataNoTime

    private val _flightConnectionGateTimeDataNoWalkTime =
        MutableLiveData<FlightConnectionGateTimeData>()
    val flightConnectionGateTimeDataNoWalkTime = _flightConnectionGateTimeDataNoWalkTime

    private val _timeBlockData =
        MutableLiveData<TimeBlockData>()
    val timeBlockData = _timeBlockData

    init {
        _flightConnectionGateTimeDataComfortable.value = FlightConnectionGateTimeData(
            pillViewTimeText = "1 hr 20 mins connection",
            pillViewBackgroundRes = R.drawable.common_ui_flight_connection_comfortable_background,
            pillViewTimeTextColor = R.color.common_ui_white,
            pillViewIconTintColor = R.color.common_ui_white,
            walkTimeDurationText = "15 min",
            walkTimeLabelText = "Walk Time",
            arrivalGateLabelText = "Arrival gate",
            arrivalGateValue = "F1",
            arrivalTerminalText = "Terminal 1,\nConcourse B",
            departureGateLabelText = "Departure gate",
            departureGateValue = "E33",
            departureTerminalText = "Terminal 1,\nConcourse B",
            conundrumText = "",
            conundrumIconType = ConundrumIconType.NONE,
        )

        _flightConnectionGateTimeDataTight.value = FlightConnectionGateTimeData(
            pillViewTimeText = "1 hr 20 mins connection",
            pillViewBackgroundRes = R.drawable.common_ui_flight_connection_tight_background,
            pillViewTimeTextColor = R.color.common_ui_black,
            pillViewIconTintColor = R.color.common_ui_black,
            walkTimeDurationText = "15 min",
            walkTimeLabelText = "Walk Time",
            arrivalGateLabelText = "Arrival gate",
            arrivalGateValue = "F1",
            arrivalTerminalText = "Terminal 1,\nConcourse B",
            departureGateLabelText = "Departure gate",
            departureGateValue = "E33",
            departureTerminalText = "Terminal 1,\nConcourse B",
            conundrumText = "",
            conundrumIconType = ConundrumIconType.NONE,
        )
        _flightConnectionGateTimeDataNoTime.value = FlightConnectionGateTimeData(
            pillViewTimeText = "",
            pillViewBackgroundRes = R.drawable.common_ui_flight_connection_comfortable_background,
            pillViewTimeTextColor = R.color.common_ui_white,
            pillViewIconTintColor = R.color.common_ui_white,
            walkTimeDurationText = "15 min",
            walkTimeLabelText = "Walk Time",
            arrivalGateLabelText = "Arrival gate",
            arrivalGateValue = "F1",
            arrivalTerminalText = "Terminal 1,\nConcourse B",
            departureGateLabelText = "Departure gate",
            departureGateValue = "E33",
            departureTerminalText = "Terminal 1,\nConcourse B",
            conundrumText = "",
            conundrumIconType = ConundrumIconType.NONE,
        )
        _flightConnectionGateTimeDataNoWalkTime.value = FlightConnectionGateTimeData(
            pillViewTimeText = "1 hr 20 mins connection",
            pillViewBackgroundRes = R.drawable.common_ui_flight_connection_comfortable_background,
            pillViewTimeTextColor = R.color.common_ui_white,
            pillViewIconTintColor = R.color.common_ui_white,
            walkTimeDurationText = "",
            walkTimeLabelText = "",
            arrivalGateLabelText = "Arrival gate",
            arrivalGateValue = "F1",
            arrivalTerminalText = "Terminal 1,\nConcourse B",
            departureGateLabelText = "Departure gate",
            departureGateValue = "E33",
            departureTerminalText = "Terminal 1,\nConcourse B",
            conundrumText = "",
            conundrumIconType = ConundrumIconType.NONE,
        )
        _timeBlockData.value = TimeBlockData(
            headerText = "Boarding",
            contentText = "Starts soon",
            flashCount = 4,
            timeState = TimeState.A,
            doAnimateView = true,
        )
    }

    fun navigateToConnectionTimeInfo() {
        navigateTo(
            ConnectionTimeInfoFragmentDestination(
                connectionTimeData = ConnectionTimeData(
                    header = "Connection Time",
                    body = "1 hr 20 mins connection",
                ),
            ),
        )
    }
}