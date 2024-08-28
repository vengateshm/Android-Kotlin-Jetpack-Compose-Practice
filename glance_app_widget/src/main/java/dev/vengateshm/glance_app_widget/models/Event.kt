package dev.vengateshm.glance_app_widget.models

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("ComX")
    val comX: Int,
    @SerializedName("ECo")
    val eCo: String,
    @SerializedName("EO")
    val eO: Int,
    @SerializedName("Eact")
    val eact: Int,
    @SerializedName("Ebat")
    val ebat: Int,
    @SerializedName("Ecov")
    val ecov: Int,
    @SerializedName("Ehid")
    val ehid: Int,
    @SerializedName("Eid")
    val eid: String,
    @SerializedName("Epr")
    val epr: Int,
    @SerializedName("Eps")
    val eps: String,
    @SerializedName("EpsL")
    val epsL: String,
    @SerializedName("ErnInf")
    val ernInf: String,
    @SerializedName("Esd")
    val esd: Long,
    @SerializedName("Ese")
    val ese: Long,
    @SerializedName("Esid")
    val esid: Int,
    @SerializedName("Et")
    val et: Int,
    @SerializedName("EtTx")
    val etTx: String,
    @SerializedName("Exd")
    val exd: Int,
    @SerializedName("IncsX")
    val incsX: Int,
    @SerializedName("LuC")
    val luC: Int,
    @SerializedName("LuUT")
    val luUT: Long,
    @SerializedName("LuX")
    val luX: Int,
    @SerializedName("Pid")
    val pid: Int,
    @SerializedName("Pids")
    val pids: dev.vengateshm.glance_app_widget.models.Pids,
    @SerializedName("SDFowX")
    val sDFowX: Int,
    @SerializedName("SDInnX")
    val sDInnX: Int,
    @SerializedName("Sids")
    val sids: dev.vengateshm.glance_app_widget.models.Sids,
    @SerializedName("Spid")
    val spid: Int,
    @SerializedName("StatX")
    val statX: Int,
    @SerializedName("Stg")
    val stg: dev.vengateshm.glance_app_widget.models.Stg,
    @SerializedName("SubsX")
    val subsX: Int,
    @SerializedName("T1")
    val t1: List<dev.vengateshm.glance_app_widget.models.T1>,
    @SerializedName("T2")
    val t2: List<dev.vengateshm.glance_app_widget.models.T2>,
    @SerializedName("TCho")
    val tCho: Int,
    @SerializedName("TPa")
    val tPa: Int,
    @SerializedName("Tr1C1")
    val tr1C1: Int,
    @SerializedName("Tr1CO1")
    val tr1CO1: Double,
    @SerializedName("Tr1CW1")
    val tr1CW1: Int,
    @SerializedName("Tr2C1")
    val tr2C1: Int,
    @SerializedName("Tr2C2")
    val tr2C2: Int,
    @SerializedName("Tr2CO1")
    val tr2CO1: Double,
    @SerializedName("Tr2CO2")
    val tr2CO2: Double,
    @SerializedName("Tr2CW1")
    val tr2CW1: Int,
    @SerializedName("Tr2CW2")
    val tr2CW2: Int
)
