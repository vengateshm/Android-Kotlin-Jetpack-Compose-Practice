package dev.vengateshm.compose_material3.api_kotlin.mutex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun BankMutexSample(modifier: Modifier = Modifier) {
//    val bank = remember { Bank() }
    val bank = remember { BankSingleton.getInstance() }
    val balance = bank.currentBalance
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = balance.value.toString())
        Button(
            onClick = {
                scope.launch {
                    bank.deposit()
                }
            }) {
            Text(text = "Deposit")
        }
        Button(
            onClick = {
                repeat(3) {
                    scope.launch {
                        bank.spend()
                    }
                }
            }) {
            Text(text = "Spend")
        }
    }
}