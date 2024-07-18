package dev.vengateshm.compose_material3.ui_concepts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalCarouselSample(modifier: Modifier = Modifier) {
    val carouselState = rememberCarouselState(initialItem = 1) { 3 }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalMultiBrowseCarousel(
            state = carouselState,
            preferredItemWidth = 300.dp,
            itemSpacing = 16.dp
        ) { page ->
            Image(
                modifier = Modifier.size(300.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(
                    id = when (page) {
                        0 -> R.drawable.cmaterial3_scene_1
                        1 -> R.drawable.cmaterial3_scene_2
                        else -> R.drawable.cmaterial3_scene_3
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}