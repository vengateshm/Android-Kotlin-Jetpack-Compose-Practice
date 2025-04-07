package dev.vengateshm.booking.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Booking(
    val id: String,
    val userId: Int,
    val status: String,
    val date: String,
) : Parcelable
