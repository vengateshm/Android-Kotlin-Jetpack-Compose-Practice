package dev.vengateshm.glance_app_widget.models


import com.google.gson.annotations.SerializedName

data class LiveMatchesResponse(
    @SerializedName("Stages")
    val stages: List<dev.vengateshm.glance_app_widget.models.Stage>,
)