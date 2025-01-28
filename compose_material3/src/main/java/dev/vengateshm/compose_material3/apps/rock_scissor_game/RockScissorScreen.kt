package dev.vengateshm.compose_material3.apps.rock_scissor_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RockScissorScreen(
    viewModel: RockScissorViewModel = viewModel(),
) {
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        GameTitle(uiState.resultText)
        Spacer(modifier = Modifier.height(16.dp))
        ComputerChoiceDisplay(uiState.computerChoice)
        Spacer(modifier = Modifier.height(32.dp))
        GameButtons(viewModel::playGame)
        Spacer(modifier = Modifier.height(32.dp))
        ScoreDisplay(uiState)
        Spacer(modifier = Modifier.height(16.dp))
        ResetButton(viewModel::resetScores)
    }
}

@Composable
private fun GameTitle(resultText: String) {
    Text(
        text = resultText,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun ComputerChoiceDisplay(computerChoice: String) {
    Text(
        text = computerChoice,
        fontSize = 18.sp,
    )
}

@Composable
private fun GameButtons(onChoiceSelected: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp),
    ) {
        Button(onClick = { onChoiceSelected("Rock") }) {
            Text("Rock")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = { onChoiceSelected("Paper") }) {
            Text("Paper")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = { onChoiceSelected("Scissors") }) {
            Text("Scissors")
        }
    }
}

@Composable
private fun ScoreDisplay(uiState: RockScissorUiData) {
    Text(
        text = "Score: You ${uiState.userScore} - ${uiState.computerScore} Computer | Ties: ${uiState.tieCount}",
        fontSize = 18.sp,
    )
}

@Composable
private fun ResetButton(onReset: () -> Unit) {
    Button(onClick = onReset) {
        Text("Reset Scores")
    }
}