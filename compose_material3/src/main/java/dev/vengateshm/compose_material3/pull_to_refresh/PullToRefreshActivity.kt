package dev.vengateshm.compose_material3.pull_to_refresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PullToRefreshActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var canRefresh by remember { mutableStateOf(false) }

                    val items = remember {
                        (1..100).map { "Item $it" }
                    }

                    val scope = rememberCoroutineScope()

                    Box(modifier = Modifier.fillMaxSize()) {
                        PullToRefreshLazyColumn(
                            items = items,
                            canRefresh = canRefresh,
                            onRefresh = {
                                scope.launch {
                                    canRefresh = true
                                    delay(3000)
                                    canRefresh = false
                                }
                            }
                        ) { item ->
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = item
                            )
                        }
                        Button(
                            modifier = Modifier.align(alignment = Alignment.BottomCenter),
                            onClick = {
                                canRefresh = true
                            }) {
                            Text(
                                text = "Refresh"
                            )
                        }
                    }
                }
            }
        }
    }
}