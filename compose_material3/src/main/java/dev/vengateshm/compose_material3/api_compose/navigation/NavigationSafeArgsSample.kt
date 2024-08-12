package dev.vengateshm.compose_material3.api_compose.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
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
                        userType = UserType.entries.toTypedArray().random()
                    )
                )
            },
                goToSettings = {
                    navController.navigate(
                        Screen.Settings(
                            config = SettingsConfig(
                                isDarkTheme = false,
                                menu = listOf("Logout, Download over WiFi only"),
                                userType = UserType.entries.toTypedArray().random()
                            )
                        )
                    )
                })
        }
        composable<Screen.Profile>(
            typeMap = mapOf(typeOf<UserType>() to NavType.EnumType(UserType::class.java))
        ) { backStackEntry ->
            val profile = backStackEntry.toRoute<Screen.Profile>()
            ProfileScreen(
                id = profile.id,
                userType = profile.userType,
                goBack = {
                    navController.popBackStack()
                })
        }
        composable<Screen.Settings>(
            typeMap = mapOf(
                typeOf<SettingsConfig>() to settingsConfigNavType,
                typeOf<UserType>() to NavType.EnumType(UserType::class.java)
            )
        ) { backStackEntry ->
            val settingsViewModel = viewModel<SettingsViewModel> {
                val savedStateHandle = createSavedStateHandle()
                SettingsViewModel(savedStateHandle = savedStateHandle)
            }

            SettingsScreen(
                viewModel = settingsViewModel,
                goBack = {
                    navController.popBackStack()
                })
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    goToProfile: () -> Unit,
    goToSettings: () -> Unit
) {
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
    goBack: () -> Unit,
    userType: UserType
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Profile Screen $id\nUser type: ${userType.name.uppercase()}")
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
    viewModel: SettingsViewModel,
    goBack: () -> Unit
) {
    val settingsConfig =
        viewModel.savedStateHandle.toRoute<Screen.Settings>(mapOf(typeOf<SettingsConfig>() to settingsConfigNavType)).config

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

class SettingsViewModel(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

}

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data class Profile(val id: Int, val userType: UserType) : Screen()

    @Serializable
    data class Settings(val config: SettingsConfig) : Screen()
}

@Parcelize
@Serializable
data class SettingsConfig(
    val isDarkTheme: Boolean,
    val menu: List<String>,
    val userType: UserType
) : Parcelable

val settingsConfigNavType = object : NavType<SettingsConfig>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): SettingsConfig? {
        val value = bundle.getString(key)
        value ?: return null
        return Json.decodeFromString<SettingsConfig>(value)
    }

    override fun parseValue(value: String): SettingsConfig {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: SettingsConfig): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: SettingsConfig) {
        bundle.putString(key, Json.encodeToString(value))
    }
}

enum class UserType {
    FREE,
    PREMIUM
}