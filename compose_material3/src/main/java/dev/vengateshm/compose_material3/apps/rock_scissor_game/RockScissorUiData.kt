package dev.vengateshm.compose_material3.apps.rock_scissor_game

data class RockScissorUiData(
    val resultText: String = "Choose your move!",
    val computerChoice: String = "",
    val userScore: Int = 0,
    val computerScore: Int = 0,
    val tieCount: Int = 0,
)
