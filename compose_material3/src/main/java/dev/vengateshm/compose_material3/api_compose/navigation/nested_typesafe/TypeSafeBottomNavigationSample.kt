package dev.vengateshm.compose_material3.api_compose.navigation.nested_typesafe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
object ConversationGraph

@Serializable
object UserProfileGraph

@Serializable
object ConversationList

@Serializable
data class ConversationDetail(val conversationId: Int) // deeplink /conversationdetail/{conversationId} path parameter

@Serializable
data class UserProfile(val profileId: Int = -1) // deeplink /userprofile?profileId={profileId} query option if has default values

fun NavGraphBuilder.conversationGraph(navController: NavController) {
    navigation<ConversationGraph>(startDestination = ConversationList) {
        composable<ConversationList> {
            ConversationListScreen(
                onClick = {
                    navController.navigate(ConversationDetail(conversationId = it))
                },
            )
        }
        composable<ConversationDetail> { entry ->
            val detail = entry.toRoute<ConversationDetail>()
            ConversationDetailScreen(conversationId = detail.conversationId)
        }
    }
}

fun NavGraphBuilder.userProfileGraph() {
    navigation<UserProfileGraph>(startDestination = UserProfile()) {
        composable<UserProfile> {
            UserProfileScreen(profileId = 1)
        }
    }
}

@Composable
fun TypeSafeBottomNavigationSample() {
    val bottomNavDestinations = listOf(ConversationGraph, UserProfileGraph)

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val entry by navController.currentBackStackEntryAsState()
                val currentDestination = entry?.destination
                bottomNavDestinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(destination::class) } == true,
                        onClick = { },
                        icon = {
                            Icon(
                                imageVector = if (destination is ConversationGraph) Icons.Default.ChatBubbleOutline else Icons.Default.AccountBox,
                                contentDescription = null,
                            )
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = ConversationGraph,
        ) {
            conversationGraph(navController)
            userProfileGraph()
        }
    }
}

@Composable
fun ConversationListScreen(onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = { onClick(Random.nextInt(100)) }) {
            Text(text = "Go To Detail")
        }
    }
}

@Composable
fun ConversationDetailScreen(conversationId: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "ID $conversationId")
    }
}

@Composable
fun UserProfileScreen(profileId: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "ID $profileId")
    }
}