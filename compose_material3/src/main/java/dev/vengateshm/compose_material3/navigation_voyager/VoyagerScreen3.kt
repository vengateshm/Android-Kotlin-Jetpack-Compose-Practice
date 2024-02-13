package dev.vengateshm.compose_material3.navigation_voyager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class VoyagerScreen3 : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Screen 3")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                navigator?.pop()
            }) {
                Text(text = "Go back")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                navigator?.popUntilRoot()
            }) {
                Text(text = "Go back to home")
            }
        }
    }
}