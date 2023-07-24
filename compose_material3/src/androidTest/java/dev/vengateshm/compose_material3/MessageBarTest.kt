package dev.vengateshm.compose_material3

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vengateshm.compose_material3.message_bar.ContentWithMessageBar
import dev.vengateshm.compose_material3.message_bar.MessageBarState
import dev.vengateshm.compose_material3.message_bar.TestTags.COPY_BUTTON
import dev.vengateshm.compose_material3.message_bar.TestTags.MESSAGE_BAR
import dev.vengateshm.compose_material3.message_bar.TestTags.MESSAGE_BAR_TEXT
import dev.vengateshm.compose_material3.message_bar.rememberMessageBarState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MessageBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var state: MessageBarState
    private lateinit var clipboardManager: ClipboardManager

    @Test
    fun test_no_message_bar_shown() {
        composeTestRule.setContent {
            state = rememberMessageBarState()
            ContentWithMessageBar(messageBarState = state) {

            }
        }
        composeTestRule.onNodeWithTag(MESSAGE_BAR).assertDoesNotExist()
    }

    @Test
    fun test_success_message_shown_without_btn() {
        composeTestRule.setContent {
            state = rememberMessageBarState()
            ContentWithMessageBar(messageBarState = state) {

            }
        }
        state.addSuccess("Bookmarked Successfully!")
        composeTestRule.onNodeWithTag(MESSAGE_BAR_TEXT)
            .assertTextEquals("Bookmarked Successfully!")
        composeTestRule.onNodeWithTag(COPY_BUTTON).assertDoesNotExist()
    }

    @Test
    fun test_error_message_shown_with_copy_btn() {
        composeTestRule.setContent {
            state = rememberMessageBarState()
            ContentWithMessageBar(messageBarState = state) {

            }
        }
        state.addError(Exception("Service Unavailable!"))
        composeTestRule.onNodeWithTag(MESSAGE_BAR_TEXT)
            .assertTextEquals("Service Unavailable!")
        composeTestRule.onNodeWithTag(COPY_BUTTON).assertExists()
    }

    @Test
    fun test_error_message_copied_to_clipboard() {
        composeTestRule.setContent {
            state = rememberMessageBarState()
            clipboardManager = LocalClipboardManager.current
            ContentWithMessageBar(messageBarState = state) {

            }
        }
        state.addError(Exception("Service Unavailable!"))
        composeTestRule.onNodeWithTag(MESSAGE_BAR_TEXT)
            .assertTextEquals("Service Unavailable!")
        composeTestRule.onNodeWithTag(COPY_BUTTON).assertExists()
        composeTestRule.onNodeWithTag(COPY_BUTTON).performClick()
        assert(clipboardManager.getText()?.text == "Service Unavailable!")
    }
}