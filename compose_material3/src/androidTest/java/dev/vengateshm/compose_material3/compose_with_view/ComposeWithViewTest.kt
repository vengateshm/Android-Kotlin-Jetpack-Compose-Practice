package dev.vengateshm.compose_material3.compose_with_view

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vengateshm.compose_material3.testing.compose_with_view.ComposeWithViewRootActivity
import dev.vengateshm.compose_material3.testing.compose_with_view.PROFILE_EMAIL_TEXT_TAG
import dev.vengateshm.compose_material3.testing.compose_with_view.SIGN_IN_CONTINUE_BTN_TAG
import dev.vengateshm.compose_material3.testing.compose_with_view.SIGN_IN_EMAIL_INPUT_TAG
import dev.vengateshm.compose_material3.testing.compose_with_view.SIGN_IN_PASSWORD_INPUT_TAG
import dev.vengateshm.compose_material3.testing.compose_with_view.WELCOME_SIGN_IN_BTN_TAG
import dev.vengateshm.compose_material3.testing.compose_with_view.WELCOME_SIGN_UP_BTN_TAG
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeWithViewTest {
    @get:Rule
    val composeAndroidRule = createAndroidComposeRule<ComposeWithViewRootActivity>()

    private lateinit var idlingResource: SimpleIdlingResource

    @Before
    fun registerIdlingResource() {
        idlingResource = SimpleIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun testButtonsDisplayedInWelcomeScreen() {
        val signInBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_IN_BTN_TAG)
        val signUpBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_UP_BTN_TAG)

        signInBtn.isDisplayed()
        signUpBtn.isDisplayed()
    }

    @Test
    fun testViewsInSignInScreenInitialState() {
        val signInBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_IN_BTN_TAG)
        val signUpBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_UP_BTN_TAG)

        signInBtn.isDisplayed()
        signUpBtn.isDisplayed()

        signInBtn.performClick()

        val email = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_EMAIL_INPUT_TAG)
        val password = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_PASSWORD_INPUT_TAG)
        val continueBtn = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_CONTINUE_BTN_TAG)

        email.isDisplayed()
        password.isDisplayed()
        continueBtn.isDisplayed()
    }

    @Test
    fun testViewsInFeedActivityInitialState() {
        val signInBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_IN_BTN_TAG)
        val signUpBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_UP_BTN_TAG)

        signInBtn.isDisplayed()
        signUpBtn.isDisplayed()

        signInBtn.performClick()

        val email = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_EMAIL_INPUT_TAG)
        val password = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_PASSWORD_INPUT_TAG)
        val continueBtn = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_CONTINUE_BTN_TAG)

        email.isDisplayed()
        password.isDisplayed()
        continueBtn.isDisplayed()

        continueBtn.performClick()

        onView(withContentDescription("Button go to profile"))
            .check(matches(withText("Go to Profile")))
    }

    @Test
    fun testProfileScreenCorrectEmailDisplayed() {
        val signInBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_IN_BTN_TAG)
        val signUpBtn = composeAndroidRule.onNodeWithTag(testTag = WELCOME_SIGN_UP_BTN_TAG)

        signInBtn.isDisplayed()
        signUpBtn.isDisplayed()

        signInBtn.performClick()

        val email = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_EMAIL_INPUT_TAG)
        val password = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_PASSWORD_INPUT_TAG)
        val continueBtn = composeAndroidRule.onNodeWithTag(testTag = SIGN_IN_CONTINUE_BTN_TAG)

        email.isDisplayed()
        password.isDisplayed()
        continueBtn.isDisplayed()

        continueBtn.performClick()

        onView(withContentDescription("Button go to profile"))
            .check(matches(withText("Go to Profile")))

        onView(withContentDescription("Button go to profile")).perform(click())

        val emailProfile = composeAndroidRule.onNodeWithTag(testTag = PROFILE_EMAIL_TEXT_TAG)
        emailProfile.isDisplayed()
        emailProfile.assertTextEquals("alice@xyz.com")

        //Using espresso
        //Fails
        onView(withText("alice@xyz.com")).check(matches(isDisplayed()))
    }
}