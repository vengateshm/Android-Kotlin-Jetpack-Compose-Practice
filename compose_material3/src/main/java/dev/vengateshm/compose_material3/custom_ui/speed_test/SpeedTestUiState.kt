package dev.vengateshm.compose_material3.custom_ui.speed_test

data class SpeedTestUiState(
    val speed: String = "",
    val ping: String = "",
    val max: String = "",
    val arcValue: Float = 0f,
    val inProgress: Boolean = false
)
