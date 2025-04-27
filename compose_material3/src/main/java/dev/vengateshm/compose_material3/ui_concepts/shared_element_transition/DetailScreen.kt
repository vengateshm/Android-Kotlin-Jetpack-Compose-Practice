package dev.vengateshm.compose_material3.ui_concepts.shared_element_transition

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageId: Int,
    onClick: () -> Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AsyncImage(
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "image-$imageId"),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .height(350.dp)
                .clickable { onClick() },
            model = images[imageId - 1].photo,
            contentDescription = "Image $imageId",
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 12.dp),
        ) {
            Text(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "author-$imageId"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                text = images[imageId - 1].author,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}