package dev.vengateshm.xml_kotlin.features.flight_connection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConnectionTimeData(
    val header: String,
    val body: String,
) : Parcelable
