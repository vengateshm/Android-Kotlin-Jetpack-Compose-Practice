package dev.vengateshm.compose_material3.api_compose.navigation.nested_typesafe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun NestedTypeSafeNavigationSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestGraph.AuthGraph
    ) {
        navigation<DestGraph.AuthGraph>(startDestination = Dest.Auth1) {
            composable<Dest.Auth1> {
                Auth1 {
                    navController.navigate(Dest.Auth2)
                }
            }
            composable<Dest.Auth2> {
                Auth2 {
                    navController.navigate(DestGraph.HomeGraph)
                }
            }
        }
        navigation<DestGraph.HomeGraph>(startDestination = Dest.Home1) {
            composable<Dest.Home1> {
                Home1 {
                    navController.navigate(Dest.Home2(text = "From Home1"))
                }
            }
            composable<Dest.Home2> {
                val home2 = it.toRoute<Dest.Home2>()
                Home2(text = home2.text) {

                }
            }
        }
    }
}

@Composable
fun Auth1(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Auth1", modifier = Modifier.clickable {
            onClick()
        })
    }
}

@Composable
fun Auth2(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Auth2", modifier = Modifier.clickable {
            onClick()
        })
    }
}

@Composable
fun Home1(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home1", modifier = Modifier.clickable {
            onClick()
        })
    }
}

@Composable
fun Home2(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, modifier = Modifier.clickable {
            onClick()
        })
    }
}