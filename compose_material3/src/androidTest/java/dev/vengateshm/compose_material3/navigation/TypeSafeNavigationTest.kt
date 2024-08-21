package dev.vengateshm.compose_material3.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.toRoute
import androidx.test.espresso.Espresso
import dev.vengateshm.compose_material3.api_compose.navigation.nested_typesafe.Dest
import dev.vengateshm.compose_material3.api_compose.navigation.nested_typesafe.NestedTypeSafeNavigationSample
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class TypeSafeNavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var testNavHostController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val context = LocalContext.current
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.surface) {
                    testNavHostController = TestNavHostController(context)
                    testNavHostController.navigatorProvider.addNavigator(ComposeNavigator())
                    NestedTypeSafeNavigationSample(
                        navController = testNavHostController
                    )
                }
            }
        }
    }

    @Test
    fun verifyAuth1() {
        composeTestRule.onNodeWithText(text = "Auth1").assertIsDisplayed()
        val route = testNavHostController.currentBackStackEntry?.toRoute<Dest.Auth1>()
        assertEquals("Auth1", route.toString())
    }

    @Test
    fun verifyNavigationFromAuth1ToAuth2() {
        composeTestRule.onNodeWithText(text = "Auth1").assertIsDisplayed().performClick()
        val route = testNavHostController.currentBackStackEntry?.toRoute<Dest.Auth2>()
        assertEquals("Auth2", route.toString())
        composeTestRule.onNodeWithText(text = "Auth2").assertIsDisplayed()

        Espresso.pressBack()

        val currentScreen = testNavHostController.currentBackStackEntry?.destination?.route
        assertEquals(Dest.Auth1::class.qualifiedName, currentScreen)
    }

    @Test
    fun verifyNavigationFromAuth2ToHome1AndBackPress() {
        composeTestRule.onNodeWithText(text = "Auth1").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText(text = "Auth2").assertIsDisplayed().performClick()
        val route = testNavHostController.currentBackStackEntry?.toRoute<Dest.Home1>()
        assertEquals("Home1", route.toString())

        Espresso.pressBack()

        val currentScreen = testNavHostController.currentBackStackEntry?.destination?.route
        assertEquals(Dest.Auth2::class.qualifiedName, currentScreen)
    }

    @Test
    fun verifyNavigationFromHome1ToHome2() {
        composeTestRule.onNodeWithText(text = "Auth1").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText(text = "Auth2").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText(text = "Home1").assertIsDisplayed().performClick()
        val route = testNavHostController.currentBackStackEntry?.toRoute<Dest.Home2>()
        assertEquals("From Home1", route?.text)

        val currentBackStackArguments = testNavHostController.currentBackStackEntry?.arguments
        assertEquals("From Home1", currentBackStackArguments?.getString("text"))

        Espresso.pressBack()

        val isHome2InBackStack = testNavHostController.backStack.any { backStack ->
            backStack.destination.route == Dest.Home2::class.qualifiedName
        }
        assertFalse(isHome2InBackStack)
    }
}