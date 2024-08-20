package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun AvoidPassingStateSample(modifier: Modifier = Modifier) {
    val balance = remember {
        mutableStateOf(0f)
    }
    Column {
        AccountView1(balance.value)
        Button(onClick = { balance.value = Random.nextFloat() }) {
            Text(text = "Get Balance")
        }
    }
}

@Composable
fun AccountView(balance: MutableState<Float>) {
    balance.value = 100f
}

@Composable
fun AccountView1(balance: Float) {
    val newBalance = remember {
        mutableFloatStateOf(balance)
    }
    BalanceView(balance = newBalance)
}

@Composable
fun BalanceView(balance: State<Float>) {
    Text(text = "${balance.value}")
}

@Preview(showBackground = true)
@Composable
private fun AvoidPassingStateSamplePreview() {
    AvoidPassingStateSample()
}