package dev.vengateshm.android_kotlin_compose_practice.games.color_finder

data class GameState(
    var score: Int,
    var lifeCount: Int,
    var timeLeft: Long,
    var colorNameAndList: ColorNameAndList,
)
