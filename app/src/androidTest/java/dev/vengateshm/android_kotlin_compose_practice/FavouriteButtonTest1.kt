package dev.vengateshm.android_kotlin_compose_practice

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import dev.vengateshm.android_kotlin_compose_practice.compose_testing.FavouriteButton
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavouriteButtonTest1 {
    @get:Rule
    val composeTesRule = createComposeRule()

    @Before
    fun setUp() {
        composeTesRule.setContent {
            FavouriteButton(modifier = Modifier.size(width = 200.dp, height = 120.dp))
        }
    }

    @Test
    fun testFavouriteButton() {
        composeTesRule.onNodeWithText("Like").assertIsDisplayed()
        composeTesRule.onNodeWithTag("FAV_BTN").performClick()
        composeTesRule.onNodeWithText("Liked").assertIsDisplayed()
        composeTesRule.onNodeWithTag("FAV_BTN").performClick()
        composeTesRule.onNodeWithText("Like").assertIsDisplayed()
    }
}
