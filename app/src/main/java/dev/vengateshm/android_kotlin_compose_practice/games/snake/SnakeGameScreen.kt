package dev.vengateshm.android_kotlin_compose_practice.games.snake

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.R
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.Citrine
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.Custard
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.RoyalBlue

@Composable
fun SnakeGameScreen(
    state: SnakeGameState,
    onSnakeGameEvent: (SnakeGameEvent) -> Unit
) {

    val foodImageBitmap = ImageBitmap.imageResource(id = R.drawable.img_apple)
    val snakeImageBitmap = when (state.direction) {
        Direction.RIGHT -> ImageBitmap.imageResource(id = R.drawable.img_snake_head)
        Direction.LEFT -> ImageBitmap.imageResource(id = R.drawable.img_snake_head2)
        Direction.UP -> ImageBitmap.imageResource(id = R.drawable.img_snake_head3)
        Direction.DOWN -> ImageBitmap.imageResource(id = R.drawable.img_snake_head4)
    }

    val context = LocalContext.current
    val foodSoundMP = remember { MediaPlayer.create(context, R.raw.food) }
    val gameOverSoundMP = remember { MediaPlayer.create(context, R.raw.gameover) }

    LaunchedEffect(key1 = state.snake.size) {
        if (state.snake.size != 1) {
            foodSoundMP?.start()
        }
    }

    LaunchedEffect(key1 = state.isGameOver) {
        if (state.isGameOver) {
            gameOverSoundMP?.start()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Score: ${state.snake.size - 1}", // Subtract 1 as initial size not considered
                    style = MaterialTheme.typography.h4
                )
            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .pointerInput(state.gameState) {
                        if (state.gameState != GameState.STARTED) {
                            return@pointerInput
                        }
                        detectTapGestures { offset ->
                            onSnakeGameEvent(SnakeGameEvent.UpdateDirection(offset, size.width))
                        }
                    }
            ) {
                val cellSize = size.width / 20
                drawGameBoard(
                    cellSize = cellSize,
                    cellColor = Custard,
                    borderCellColor = RoyalBlue,
                    gridWidth = 20,
                    gridHeight = 20
                )
                drawFood(
                    foodImage = foodImageBitmap,
                    cellSize = cellSize.toInt(),
                    coordinate = state.food
                )
                drawSnake(
                    snakeHeadImage = snakeImageBitmap,
                    cellSize = cellSize,
                    snake = state.snake
                )
            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { onSnakeGameEvent(SnakeGameEvent.ResetGame) },
                    enabled = state.gameState == GameState.PAUSED || state.isGameOver
                ) {
                    Text(text = if (state.isGameOver) "Reset" else "New Game")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        when (state.gameState) {
                            GameState.IDLE, GameState.PAUSED -> onSnakeGameEvent(SnakeGameEvent.StartGame)
                            GameState.STARTED -> onSnakeGameEvent(SnakeGameEvent.PauseGame)
                        }
                    },
                    enabled = !state.isGameOver
                ) {
                    Text(
                        text = when (state.gameState) {
                            GameState.IDLE -> "Start"
                            GameState.STARTED -> "Pause"
                            GameState.PAUSED -> "Resume"
                        }
                    )
                }
            }
        }
        AnimatedVisibility(visible = state.isGameOver) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Game Over",
                style = MaterialTheme.typography.h5
            )
        }
    }
}

fun DrawScope.drawGameBoard(
    cellSize: Float,
    cellColor: Color,
    borderCellColor: Color,
    gridWidth: Int,
    gridHeight: Int,
) {
    for (i in 0 until gridWidth) {
        for (j in 0 until gridHeight) {
            val isBorderCell = (i == 0 || j == 0 || i == gridWidth - 1 || j == gridHeight - 1)
            drawRect(
                color = if (isBorderCell) borderCellColor
                else if ((i + j) % 2 == 0) cellColor
                else cellColor.copy(alpha = 0.5f),
                topLeft = Offset(x = i * cellSize, y = j * cellSize),
                size = Size(cellSize, cellSize)
            )
        }
    }
}

fun DrawScope.drawFood(
    foodImage: ImageBitmap,
    cellSize: Int,
    coordinate: Coordinate
) {
    drawImage(
        image = foodImage,
        dstOffset = IntOffset(
            x = (coordinate.x * cellSize),
            y = (coordinate.y * cellSize)
        ),
        dstSize = IntSize(cellSize, cellSize)
    )
}

fun DrawScope.drawSnake(
    snakeHeadImage: ImageBitmap,
    cellSize: Float,
    snake: List<Coordinate>
) {
    val cellSizeInt = cellSize.toInt()
    snake.forEachIndexed { index, coordinate ->
        val radius = if (index == snake.lastIndex) cellSize / 2.5f else cellSize / 2
        if (index == 0) {
            drawImage(
                image = snakeHeadImage,
                dstOffset = IntOffset(
                    x = (coordinate.x * cellSizeInt),
                    y = (coordinate.y * cellSizeInt)
                ),
                dstSize = IntSize(cellSizeInt, cellSizeInt)
            )
        } else {
            drawCircle(
                color = Citrine,
                center = Offset(
                    x = (coordinate.x * cellSize) + radius,
                    y = (coordinate.y * cellSize) + radius
                ),
                radius = radius
            )
        }
    }
}

@Preview
@Composable
fun SnakeGameScreenPreview() {
    val state = SnakeGameState()
    SnakeGameScreen(state) {}
}