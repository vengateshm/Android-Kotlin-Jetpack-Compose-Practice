package dev.vengateshm.compose_material3.ui_components.otp_input_field

data class OtpState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
    val isValid: Boolean? = null,
)
