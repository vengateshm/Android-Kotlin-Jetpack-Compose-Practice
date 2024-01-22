package dev.vengateshm.android_kotlin_compose_practice.games.snake

import kotlin.random.Random

data class SnakeGameState(
    val xAxisGridSize: Int = 20,
    val yAxisGridSize: Int = 30,
    val direction: Direction = Direction.RIGHT,
    val snake: List<Coordinate> =
        listOf(
            Coordinate(
                x = 5,
                y = 5,
            ),
        ),
    // Start position of the snake head
    val food: Coordinate = generateRandomFoodCoordinate(),
    val isGameOver: Boolean = false,
    val gameState: GameState = GameState.IDLE,
) {
    companion object {
        fun generateRandomFoodCoordinate() =
            Coordinate(
                x = Random.nextInt(from = 1, until = 19),
                y = Random.nextInt(from = 1, until = 19),
            )
    }
}
