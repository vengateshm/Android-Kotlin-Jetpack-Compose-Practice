package dev.vengateshm.android_kotlin_compose_practice.navigation_deeplink

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme

class NavDeepLinkSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Deeplinks
        // Implicit - External application to launch our app resolves via intent filter
        // Explicit - Same app opens its own screen via deeplink (Mention exact app which satisfies the deeplink)

        setContent {
            AndroidKotlinComposePracticeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                navController.navigate("detail")
                            }) {
                                Text(text = "To Detail")
                            }
                        }
                    }
                    composable(
                        route = "detail",
                        deepLinks = listOf(
                            navDeepLink {
                                uriPattern = "https://dev-vengateshm.com/{id}"
                                action = Intent.ACTION_VIEW
                            }
                        ),
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val id = it.arguments?.getInt("id")
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "The id is $id")
                        }
                    }
                }
            }
        }
    }
}