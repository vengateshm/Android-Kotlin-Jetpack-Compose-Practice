package dev.vengateshm.compose_material3.image

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.R

@Composable
fun ImageBlurSample(modifier: Modifier = Modifier) {
    val selected = remember {
        mutableStateListOf<Int>()
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(10, key = { it }) { index ->
            val isSelected = selected.contains(index)
            val blurRadiusAnimated by
            animateDpAsState(
                targetValue = if (isSelected) 20.dp else 0.dp,
                animationSpec = tween(2000),
                label = "blur_animation"
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .blur(radius = blurRadiusAnimated)
                        .clickable {
                            if (selected.contains(index)) {
                                selected.remove(index)
                            } else {
                                selected.add(index)
                            }
                        },
                    painter = painterResource(id = R.drawable.cmaterial3_someone_else),
                    contentDescription = null
                )
            }
        }
    }
}