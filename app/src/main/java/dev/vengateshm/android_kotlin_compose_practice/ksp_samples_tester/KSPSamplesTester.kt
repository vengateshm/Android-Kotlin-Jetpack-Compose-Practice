package dev.vengateshm.android_kotlin_compose_practice.ksp_samples_tester

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.ksp_samples.GsonSerialize
import kotlinx.coroutines.delay

@GsonSerialize
data class Product(val name: String, val price: Double)

@Composable
fun KSPSamplesTester() {
    val product =
        remember {
            Product("Philips Sound bar", 20000.0)
        }
    val jsonString = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        delay(1000)
        // jsonString.value = product.toJson()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = jsonString.value)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun KSPSamplesTesterPreview() {
    KSPSamplesTester()
}
