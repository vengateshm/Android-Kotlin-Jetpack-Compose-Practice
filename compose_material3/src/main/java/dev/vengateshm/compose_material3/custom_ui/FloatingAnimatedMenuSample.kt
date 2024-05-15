package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingAnimatedMenuSample() {

    var isVisible by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Colors") })
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2)
            ) {
                items(20) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .background(color = Color(Random.nextLong(0XFFFFFFFF))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "$it", style = MaterialTheme.typography.headlineLarge)
                    }
                }
            }

            VFloatingMenu(isVisible = isVisible)

            VBottomAppBar(
                isVisible = isVisible,
                onPlusIconTap = {
                    isVisible = !isVisible
                })
        }
    }
}

@Composable
fun BoxScope.VFloatingMenu(modifier: Modifier = Modifier, isVisible: Boolean) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = isVisible,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .height(250.dp),
        enter = slideInVertically(
            initialOffsetY = { with(density) { 250.dp.toPx().roundToInt() } },
            animationSpec = tween(1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { with(density) { 250.dp.toPx().roundToInt() } },
            animationSpec = tween(1000)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        0.5f to Color.White,
                        0.9f to Color.White.copy(alpha = 0.3f),
                        0.99f to Color.White.copy(alpha = 0.005f),
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0.0f
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
            ) {
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { 50 },
                                animationSpec = tween(1000)
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { 50 },
                                animationSpec = tween(1000)
                            )
                        ),
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Location Icon",
                )
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { 250 },
                                animationSpec = tween(1000)
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { 250 },
                                animationSpec = tween(1000)
                            )
                        ),
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Notifications Icon"
                )
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { 450 },
                                animationSpec = tween(1000)
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { 450 },
                                animationSpec = tween(1000)
                            )
                        ),
                    imageVector = Icons.Outlined.PersonAdd,
                    contentDescription = "Person Icon"
                )
            }
        }
    }
}

@Composable
fun BoxScope.VBottomAppBar(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onPlusIconTap: () -> Unit
) {
    val plusIconForegroundColor by animateColorAsState(
        targetValue = if (isVisible) Color.Black else Color.White,
        animationSpec = tween(durationMillis = 1000),
        label = "Plus icon foreground color animation"
    )
    val plusIconBackgroundColor by animateColorAsState(
        targetValue = if (isVisible) Color(0XFFEAEAEA) else Color.Red,
        animationSpec = tween(durationMillis = 1000),
        label = "Plus icon background color animation"
    )
    val plusIconRotation by animateFloatAsState(
        targetValue = if (isVisible) 135.0f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "Plus icon rotation animation"
    )

    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        tonalElevation = 0.dp
    ) {
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = "Location Icon"
        )
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Outlined.Mail,
            contentDescription = "Mail Icon"
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = plusIconBackgroundColor)
                .clickable {
                    onPlusIconTap()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.graphicsLayer {
                    rotationZ = plusIconRotation
                },
                imageVector = Icons.Filled.Add,
                contentDescription = "Location Icon",
                tint = plusIconForegroundColor
            )
        }
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifications Icon"
        )
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Outlined.Person,
            contentDescription = "Person Icon"
        )
    }
}

@Preview
@Composable
private fun FloatingAnimatedMenuSamplePreview() {
    FloatingAnimatedMenuSample()
}