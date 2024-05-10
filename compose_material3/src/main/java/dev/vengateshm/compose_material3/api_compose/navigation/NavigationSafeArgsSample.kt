package dev.vengateshm.compose_material3.api_compose.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.typeOf

@Composable
fun NavigationSafeArgsSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home) {
        composable<Screen.Home> {
            HomeScreen(goToProfile = {
                navController.navigate(
                    Screen.Profile(
                        id = Random.nextInt(0..10),
                    )
                )
            },
                goToSettings = {
                    navController.navigate(
                        Screen.Settings(
                            config = SettingsConfig(
                                isDarkTheme = false,
                                menu = listOf("Logout, Download over WiFi only")
                            )
                        )
                    )
                })
        }
        composable<Screen.Profile> { backStackEntry ->
            val profile = backStackEntry.toRoute<Screen.Profile>()
            ProfileScreen(
                id = profile.id,
                goBack = {
                    navController.popBackStack()
                })
        }
        composable<Screen.Settings>(
            typeMap = mapOf(typeOf<SettingsConfig>() to SettingsConfigType)
        ) { backStackEntry ->
            val settingsConfig = backStackEntry.toRoute<Screen.Settings>()

            LaunchedEffect(key1 = settingsConfig) {
                println("SettingsConfig: $SettingsConfig")
            }

            SettingsScreen(
                settingsConfig = settingsConfig.config,
                goBack = {
                    navController.popBackStack()
                })
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, goToProfile: () -> Unit, goToSettings: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home Screen")
            ElevatedAssistChip(
                onClick = { goToProfile() },
                label = { Text(text = "Go To Profile") })
            ElevatedAssistChip(
                onClick = { goToSettings() },
                label = { Text(text = "Go To Settings") })
        }
    }
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    id: Int,
    goBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Profile Screen $id")
            ElevatedAssistChip(
                onClick = dropUnlessResumed {
                    goBack()
                },
                label = { Text(text = "Go Back") })
        }
    }
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsConfig: SettingsConfig,
    goBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Settings Screen $settingsConfig")
            ElevatedAssistChip(
                onClick = dropUnlessResumed {
                    goBack()
                },
                label = { Text(text = "Go Back") })
        }
    }
}

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data class Profile(val id: Int) : Screen()

    @Serializable
    data class Settings(val config: SettingsConfig) : Screen()
}

@Serializable
@Parcelize
data class SettingsConfig(val isDarkTheme: Boolean, val menu: List<String>) : Parcelable

val SettingsConfigType = object : NavType<SettingsConfig>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): SettingsConfig? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, SettingsConfig::class.java)
        } else {
            bundle.getParcelable(key) as? SettingsConfig
        }
    }

    override fun parseValue(value: String): SettingsConfig {
        return Json.decodeFromString<SettingsConfig>(value)
    }

    override fun put(bundle: Bundle, key: String, value: SettingsConfig) {
        bundle.putParcelable(key, value)
    }
}