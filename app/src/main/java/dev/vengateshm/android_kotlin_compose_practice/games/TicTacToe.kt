package dev.vengateshm.android_kotlin_compose_practice.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class TicTacToeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    TicTacToe()
                }
            }
        }
    }
}

@Composable
fun TicTacToe() {
    val board by remember { mutableStateOf(Array(3) { arrayOfNulls<String>(3) }) }
    var currentPlayer by remember { mutableStateOf("❌") }
    var winner by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tic Tac Toe", style = MaterialTheme.typography.h4, color = Color(0XFFFF5F1F)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            for (row in 0..2) {
                Row {
                    for (col in 0..2) {
                        Button(
                            modifier = Modifier
                                .aspectRatio(ratio = 1f)
                                .weight(weight = 1f)
                                .padding(4.dp), onClick = {
                                if (board[row][col] == null && winner == null) {
                                    board[row][col] = currentPlayer
                                    currentPlayer = currentPlayer.switchPlayer()
                                    winner = board.checkWinner()
                                }
                            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                        ) {
                            Text(
                                text = board[row][col] ?: "",
                                style = MaterialTheme.typography.h5,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Current Player : $currentPlayer", color = Color.White
        )
        winner?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Winner : $winner", color = Color.White
            )
            LaunchedEffect(true) {
                // Reset if there is a winner
                winner?.let {
                    delay(2000L)
                    winner = null
                    currentPlayer = "❌"
                    board.fill(arrayOfNulls<String?>(3))
                }
            }
            Button(
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                onClick = {
                    winner = null
                    currentPlayer = "❌"
                    board.fill(arrayOfNulls<String?>(3))
                }) {
                Text(
                    text = "RESET",
                    color = Color.White
                )
            }
        }

        /*LaunchedEffect(key1 = winner) {
            // Reset if there is a winner
            winner?.let {
                delay(500L)
                winner = null
                currentPlayer = "❌"
                board.fill(arrayOfNulls<String?>(3))
            }
        }*/
    }
}

fun String.switchPlayer() = if (this == "❌") "⭕" else "❌"

fun Array<Array<String?>>.checkWinner(): String? {
    for (row in 0..2) {
        if (this[row][0] != null && this[row][0] == this[row][1] && this[row][1] == this[row][2]) {
            return this[row][0]
        }
    }
    for (col in 0..2) {
        if (this[0][col] != null && this[0][col] == this[1][col] && this[1][col] == this[2][col]) {
            return this[0][col]
        }
    }
    if (this[0][0] != null && this[0][0] == this[1][1] && this[1][1] == this[2][2]) {
        return this[0][0]
    }
    if (this[0][2] != null && this[0][2] == this[1][1] && this[1][1] == this[2][0]) {
        return this[0][2]
    }
    return null
}