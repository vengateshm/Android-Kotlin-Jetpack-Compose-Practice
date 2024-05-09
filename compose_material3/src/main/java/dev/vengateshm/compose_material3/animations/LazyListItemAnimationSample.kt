package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LazyListItemAnimationSample(modifier: Modifier = Modifier) {

    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedAssistChip(
                onClick = { items.add("Item ${items.size + 1}") },
                label = { Text(text = "Add item") })
            ElevatedAssistChip(onClick = { items.shuffle() }, label = { Text(text = "Shuffle") })
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = items, key = { item -> item }) { item ->
                Text(
                    text = item, fontSize = 26.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            items.remove(item)
                        }
                        .animateItem(
                            fadeInSpec = tween(durationMillis = 1000),
                            placementSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                            fadeOutSpec = tween(durationMillis = 1000)
                        )
                )
            }
        }
    }
}