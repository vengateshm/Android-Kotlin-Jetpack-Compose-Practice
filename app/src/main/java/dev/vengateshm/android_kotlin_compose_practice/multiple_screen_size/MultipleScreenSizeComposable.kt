package dev.vengateshm.android_kotlin_compose_practice.multiple_screen_size

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultipleScreenSizeComposable() {
    val windowInfo = rememberWindowInfo()
    if (windowInfo.screenWidthType is WindowInfo.WindowType.Compact) {
        LazyColumn(content = {
            items(10) { item ->
                Text(
                    text = "Item $item",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .padding(16.dp)
                )
            }
            items(10) { item ->
                Text(
                    text = "Item $item",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(16.dp)
                )
            }
        })
    } else {
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                content = {
                    items(10) { item ->
                        Text(
                            text = "Item $item",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(16.dp)
                        )
                    }
                })
            LazyColumn(
                modifier = Modifier.weight(1f),
                content = {
                    items(10) { item ->
                        Text(
                            text = "Item $item",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.LightGray)
                                .padding(16.dp)
                        )
                    }
                })
        }
    }
}