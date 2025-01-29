package dev.vengateshm.xml_kotlin.custom_views.flight_connection_gate_time_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.databinding.FragmentFlightConnectionGateTimeBinding

class FlightConnectionGateTimeFragment : Fragment() {

    private var _binding: FragmentFlightConnectionGateTimeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFlightConnectionGateTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            flightConnectionGateTimeViewComfortable.setData(
                FlightConnectionGateTimeData(
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
                ),
            )
            flightConnectionGateTimeViewTight.setData(
                FlightConnectionGateTimeData(
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
                ),
            )
            flightConnectionGateTimeViewNoTime.setData(
                FlightConnectionGateTimeData(
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
                ),
            )
            flightConnectionGateTimeViewNoWalkTime.setData(
                FlightConnectionGateTimeData(
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
                ),
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}