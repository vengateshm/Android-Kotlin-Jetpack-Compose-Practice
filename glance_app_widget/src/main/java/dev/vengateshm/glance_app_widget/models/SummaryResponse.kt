package dev.vengateshm.glance_app_widget.models

import com.google.gson.annotations.SerializedName

data class SummaryResponse(
    @SerializedName("Message") val message: String,
    @SerializedName("Global") val global: dev.vengateshm.glance_app_widget.models.Global,
    @SerializedName("Countries") val countries: List<dev.vengateshm.glance_app_widget.models.Countries>,
    @SerializedName("Date") val date: String,
)
