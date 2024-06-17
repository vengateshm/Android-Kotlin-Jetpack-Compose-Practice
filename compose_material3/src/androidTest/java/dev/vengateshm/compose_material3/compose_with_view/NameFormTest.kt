package dev.vengateshm.compose_material3.compose_with_view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vengateshm.compose_material3.testing.compose_with_view.NAME_FORM_CONTINUE_BTN_TAG
import dev.vengateshm.compose_material3.testing.compose_with_view.NAME_FORM_INPUT_TAG
import dev.vengateshm.compose_material3.testing.compose_with_view.NameForm
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NameFormTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NameForm()
                }
            }
        }
    }

    @Test
    fun testNameFormButtonEnabled() {
        val expectedName = "John"
        val inputField = composeRule.onNodeWithTag(NAME_FORM_INPUT_TAG)
        val continueBtn = composeRule.onNodeWithTag(NAME_FORM_CONTINUE_BTN_TAG)

        inputField.assertTextEquals("Name", includeEditableText = false)
        continueBtn.assertIsNotEnabled()

        inputField.performTextInput(expectedName)
        inputField.assertTextContains(expectedName)
        continueBtn.assertIsEnabled()
    }
}