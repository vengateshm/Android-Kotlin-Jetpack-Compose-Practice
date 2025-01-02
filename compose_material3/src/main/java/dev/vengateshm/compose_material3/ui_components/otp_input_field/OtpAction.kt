package dev.vengateshm.compose_material3.ui_components.otp_input_field

sealed interface OtpAction {
    data class OnEnterNumber(val number: Int?, val index: Int) : OtpAction
    data class OnChangeFieldFocused(val index: Int) : OtpAction
    data object OnKeyboardBack : OtpAction
}