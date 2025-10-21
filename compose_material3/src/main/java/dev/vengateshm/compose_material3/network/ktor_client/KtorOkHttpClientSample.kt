package dev.vengateshm.compose_material3.network.ktor_client

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.vengateshm.compose_material3.network.ktor_client.KtorHttpClient.okHttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

@Composable
fun KtorOkHttpClientSample(modifier: Modifier = Modifier) {
  var apiResponseText by remember { mutableStateOf("") }

  LaunchedEffect(Unit) {
    val httpResponse =
      okHttpClient.get(urlString = "https://jsonplaceholder.typicode.com/todos/100")
    apiResponseText = httpResponse.bodyAsText()
  }

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(text = apiResponseText)
  }
}