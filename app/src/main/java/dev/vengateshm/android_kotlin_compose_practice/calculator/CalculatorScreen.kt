package dev.vengateshm.android_kotlin_compose_practice.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.calculator.ui.MediumGray

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {
    val state = viewModel.state
    val buttonSpacing = 8.dp

    Calculator(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MediumGray)
                .padding(16.dp),
        state = state,
        buttonSpacing = buttonSpacing,
        onAction = viewModel::onAction,
    )
}
