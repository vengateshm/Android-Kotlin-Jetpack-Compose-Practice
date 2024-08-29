package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import kotlin.random.Random

@Composable
fun MultipleProfileAvatars(modifier: Modifier = Modifier) {
    val images = remember {
        listOf(
            "https://plus.unsplash.com/premium_photo-1658527049634-15142565537a?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YXZhdGFyfGVufDB8fDB8fHww",
            "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8YXZhdGFyfGVufDB8fDB8fHww",
            "https://images.unsplash.com/photo-1706885093487-7eda37b48a60?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTl8fGF2YXRhcnxlbnwwfHwwfHx8MA%3D%3D",
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            images.forEachIndexed { index, url ->
                AsyncImage(
                    model = url,
                    modifier = Modifier
                        .size(52.dp)
                        .zIndex((3 - index).toFloat())
                        .offset(x = -(index.dp * 16))
                        .clip(CircleShape)
                        .background(color = Color(Random.nextLong()))
                        .border(width = 1.dp, color = Color.White, shape = CircleShape),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}