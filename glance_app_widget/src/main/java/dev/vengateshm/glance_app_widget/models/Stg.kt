package dev.vengateshm.glance_app_widget.models

import com.google.gson.annotations.SerializedName

data class Stg(
    @SerializedName("Ccd")
    val ccd: String,
    @SerializedName("Ccdiso")
    val ccdiso: String,
    @SerializedName("Chi")
    val chi: Int,
    @SerializedName("Cid")
    val cid: String,
    @SerializedName("Cnm")
    val cnm: String,
    @SerializedName("Csnm")
    val csnm: String,
    @SerializedName("Scd")
    val scd: String,
    @SerializedName("Scu")
    val scu: Int,
    @SerializedName("Sdn")
    val sdn: String,
    @SerializedName("Sds")
    val sds: String,
    @SerializedName("Shi")
    val shi: Int,
    @SerializedName("Sid")
    val sid: String,
    @SerializedName("Snm")
    val snm: String
)
