package dev.vengateshm.xml_kotlin.custom_views.flight_connection_gate_time_view

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class FlightConnectionGateTimeData(
    val pillViewTimeText: String,
    @DrawableRes val pillViewBackgroundRes: Int,
    @ColorRes val pillViewTimeTextColor: Int,
    @ColorRes val pillViewIconTintColor: Int,
    val walkTimeDurationText: String,
    val walkTimeLabelText: String,
    val arrivalGateLabelText: String,
    val arrivalGateValue: String,
    val arrivalTerminalText: String,
    val departureGateLabelText: String,
    val departureGateValue: String,
    val departureTerminalText: String,
)