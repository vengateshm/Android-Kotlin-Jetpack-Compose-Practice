package dev.vengateshm.compose_material3

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vengateshm.compose_material3.testing.StateRestorationTestSampleWithViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StateRestorationTestSampleWithViewModelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val stateRestorationTester = StateRestorationTester(composeTestRule)

    @Test
    fun testRestoration() {
        stateRestorationTester.setContent {
            StateRestorationTestSampleWithViewModel()
        }
        composeTestRule.onNodeWithTag("TT").assertTextEquals("Loading...")
        composeTestRule.onNodeWithTag("BB").performClick()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithTag("TT").assertTextEquals("Loaded")
    }
}