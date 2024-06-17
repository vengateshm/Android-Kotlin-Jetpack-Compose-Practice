package dev.vengateshm.compose_material3.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dev.vengateshm.compose_material3.testing.navigation.NavHostTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = TestNavHostController(LocalContext.current)
                    navController.navigatorProvider.addNavigator(ComposeNavigator())
                    NavHostTest(navHostController = navController)
                }
            }
        }
    }

    @Test
    fun verify_StartDestinationIsS1() {
        composeTestRule.onNodeWithText("Go to S2")
            .assertIsDisplayed()
    }

    @Test
    fun verify_NavigationFromS1ToS2() {
        composeTestRule.onNodeWithText("Go to S2")
            .assertIsDisplayed()
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route

        composeTestRule.onNodeWithText("Hello! Good Morning!")
            .assertIsDisplayed()

        assertEquals("s2/{value1}/{value2}", route)
    }
}