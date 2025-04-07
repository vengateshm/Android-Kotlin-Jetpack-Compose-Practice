package dev.vengateshm.appcore.comm

data class CommData(
    val originatePath: CommPath,
    val destinationPath: CommPath,
    val requestCode: String,
    val requestType: CommType,
    val data: Any?,
    val tag: String? = null,
)
