package dev.vengateshm.glance_app_widget.models


import com.google.gson.annotations.SerializedName

data class PidsX(
    @SerializedName("11")
    val x11: List<String>,
    @SerializedName("12")
    val x12: List<String>,
    @SerializedName("8")
    val x8: List<String>,
)