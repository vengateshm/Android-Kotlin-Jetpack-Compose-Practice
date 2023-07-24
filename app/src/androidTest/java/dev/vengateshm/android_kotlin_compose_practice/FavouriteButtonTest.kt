package dev.vengateshm.android_kotlin_compose_practice

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.vengateshm.android_kotlin_compose_practice.compose_testing.ComposeTestingActivity
import org.junit.Rule
import org.junit.Test

class FavouriteButtonTest {
    @get:Rule
    val composeTesRule = createAndroidComposeRule<ComposeTestingActivity>()

    @Test
    fun testFavouriteButton() {
        composeTesRule.onNodeWithText("Like").assertIsDisplayed()
        composeTesRule.onNodeWithTag("FAV_BTN").performClick()
        composeTesRule.onNodeWithText("Liked").assertIsDisplayed()
        composeTesRule.onNodeWithTag("FAV_BTN").performClick()
        composeTesRule.onNodeWithText("Like").assertIsDisplayed()
    }
}