package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

const val FAB_SHARED_BOUNDS_KEY = "fab_shared_bounds"

@Serializable
data object MainContent

@Serializable
data object AddItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FabSharedTransition(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val fabColor = Color.Green

    SharedTransitionLayout {
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = MainContent,
        ) {
            composable<MainContent> {
                MainContentScreen(
                    onFabClick = {
                        navController.navigate(AddItem)
                    },
                    fabColor = fabColor,
                    animatedVisibilityScope = this,
                )
            }
            composable<AddItem> {
                AddItemScreen(
                    fabColor = fabColor,
                    animatedVisibilityScope = this,
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MainContentScreen(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit,
    fabColor: Color,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = fabColor,
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(
                        key = FAB_SHARED_BOUNDS_KEY,
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item",
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Main Content")
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AddItemScreen(
    modifier: Modifier = Modifier,
    fabColor: Color,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fabColor)
            .sharedBounds(
                sharedContentState = rememberSharedContentState(
                    key = FAB_SHARED_BOUNDS_KEY,
                ),
                animatedVisibilityScope = animatedVisibilityScope,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Add Item")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FabSharedTransitionPreview() {
    FabSharedTransition()
}