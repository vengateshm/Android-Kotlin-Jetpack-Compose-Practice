package dev.vengateshm.glance_app_widget.models


import com.google.gson.annotations.SerializedName

data class T2(
    @SerializedName("Gd")
    val gd: Int,
    @SerializedName("HasVideo")
    val hasVideo: Boolean,
    @SerializedName("ID")
    val iD: String,
    @SerializedName("Nm")
    val nm: String,
    @SerializedName("Pids")
    val pids: dev.vengateshm.glance_app_widget.models.PidsXX,
    @SerializedName("tbd")
    val tbd: Int,
)