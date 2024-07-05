package dev.vengateshm.compose_material3.custom_ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableFABSample(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExpandableFAB()
        }
    ) {
        println(it)
    }
}

@Composable
fun ExpandableFAB(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val items = remember {
        listOf(
            ExpandableFABItem(imageVector = Icons.Filled.Home, title = "Home"),
            ExpandableFABItem(imageVector = Icons.Filled.Person, title = "Person"),
            ExpandableFABItem(imageVector = Icons.Filled.Build, title = "Settings"),
        )
    }
    val transition = updateTransition(targetState = expanded, label = "transition")
    val rotation by transition.animateFloat(label = "rotation") { isExpanded ->
        if (isExpanded) 315f else 0f
    }
    Column(horizontalAlignment = Alignment.End) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }) + expandVertically(),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it }) + shrinkVertically()
        ) {
            LazyColumn {
                items(items.size) { index ->
                    val item = items[index]
                    ExpandedFABItemUI(
                        imageVector = item.imageVector,
                        title = item.title
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.End)
                .graphicsLayer {
                    rotationZ = rotation
                },
            onClick = { expanded = !expanded },
            containerColor = Color(0XFFFF9800)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
fun ExpandedFABItemUI(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    title: String
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier.border(
                width = 2.dp,
                color = Color(0XFFFF9800),
                shape = RoundedCornerShape(10.dp)
            )
        ) {
            Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), text = title)
        }
        Spacer(modifier = Modifier.width(16.dp))
        FloatingActionButton(
            shape = CircleShape,
            containerColor = Color(0XFFFF9800),
            onClick = {
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(imageVector = imageVector, contentDescription = "$title icon")
        }
    }
}

data class ExpandableFABItem(val imageVector: ImageVector, val title: String)