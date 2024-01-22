package dev.vengateshm.android_kotlin_compose_practice.games.color_finder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme

class ColorFinderActivity : ComponentActivity() {
    private val viewModel: ColorFinderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidKotlinComposePracticeTheme {
                ColorFinderScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorFinderScreen(viewModel: ColorFinderViewModel) {
    val state = viewModel.gameState.value
    val gameOverDialogVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = state.lifeCount, key2 = state.timeLeft) {
        if (state.lifeCount == 0 || state.timeLeft == 0L) {
            gameOverDialogVisible.value = true
        }
    }

    if (gameOverDialogVisible.value) {
        viewModel.stopTimer()
        GameOverDialog(
            score = state.score,
            onRestartClicked = {
                gameOverDialogVisible.value = false
                viewModel.resetGame()
            },
        )
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            LifeCountDisplay(lifeCount = state.lifeCount)
            TimerDisplay(timeLeft = state.timeLeft)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ColorNameDisplay(
            colorName = state.colorNameAndList.colorName,
            color = state.colorNameAndList.colorNameTextColor,
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            state.colorNameAndList.colorList.forEach { data ->
                ColoredBox(
                    data = data,
                    onClick = { clickedColorName ->
                        viewModel.onBoxClicked(clickedColorName == state.colorNameAndList.colorName)
                    },
                )
            }
        }
    }
}

@Composable
fun TimerDisplay(timeLeft: Long) {
    Text(
        text = "Time Left: $timeLeft s",
        fontSize = 20.sp,
    )
}

@Composable
fun LifeCountDisplay(lifeCount: Int) {
    Text(
        text = "Life Count: $lifeCount",
        fontSize = 20.sp,
    )
}

@Composable
fun ColorNameDisplay(
    colorName: String,
    color: Color,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = colorName,
        fontSize = 20.sp,
        color = color,
    )
}

@Composable
fun ColoredBox(
    data: Pair<String, Color>,
    onClick: (String) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .size(100.dp)
                .background(
                    color = data.second,
                    shape = RoundedCornerShape(8.dp),
                )
                .clickable { onClick(data.first) },
    )
}

@Composable
fun GameOverDialog(
    score: Int,
    onRestartClicked: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { /* Dismiss the dialog by clicking outside */ },
        title = { Text(text = "Game Over") },
        text = { Text(text = "Your Score: $score") },
        confirmButton = {
            Button(
                onClick = {
                    onRestartClicked()
                },
            ) {
                Text(text = "Restart")
            }
        },
    )
}
