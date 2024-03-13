package dev.vengateshm.compose_material3.multiple_screen_size

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MultipleScreenSupportSample() {
    val windowSize = getWindowSize()

    when (windowSize.width) {
        WindowType.Compact -> {
            CompactWidthScreen()
        }

        WindowType.Medium -> {
            MediumWidthScreen()
        }

        WindowType.Expanded -> {

        }
    }
}

@Composable
private fun MediumWidthScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Box(
                    modifier = Modifier
                        .size(width = 150.dp, height = 20.dp)
                        .background(color = Color.LightGray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 18.dp)
                        .background(color = Color.LightGray)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(width = 200.dp, height = 15.dp)
                        .background(color = Color.LightGray)
                )
            }
        }
    }
}

@Composable
private fun CompactWidthScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 20.dp)
                    .background(color = Color.LightGray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 18.dp)
                    .background(color = Color.LightGray)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .size(width = 200.dp, height = 15.dp)
                    .background(color = Color.LightGray)
            )
        }
    }
}