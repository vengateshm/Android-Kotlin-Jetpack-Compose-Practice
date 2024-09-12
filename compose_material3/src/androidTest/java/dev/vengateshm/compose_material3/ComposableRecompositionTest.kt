package dev.vengateshm.compose_material3

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

data class User(val name: String, val age: Int)

@Composable
fun ParentComposable(
    sample: User,
    onParentRecompose: () -> Unit,
    onChildRecompose: () -> Unit,
) {
    SideEffect {
        onParentRecompose.invoke()
    }
    ChildComposable(name = sample.name) {
        onChildRecompose.invoke()
    }
}

@Composable
fun ChildComposable(name: String, onChildRecompose: () -> Unit) {
    SideEffect {
        onChildRecompose.invoke()
    }
    Text(text = name)
}

class ComposableRecompositionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun childComposable_should_be_skipped() {
        var sample by mutableStateOf(User("Vengateshm", 30))
        var parentRecomposeCount = 0
        var childRecomposeCount = 0
        composeTestRule.setContent {
            ParentComposable(
                sample = sample,
                onParentRecompose = remember { { parentRecomposeCount++ } },
                onChildRecompose = remember { { childRecomposeCount++ } },
            )
        }
        childRecomposeCount = 0
        parentRecomposeCount = 0

        sample = sample.copy(age = 31)
        composeTestRule.waitForIdle()

        assertEquals(1, parentRecomposeCount)
        assertEquals(0, childRecomposeCount)
    }

    @Test
    fun childComposable_should_not_be_skipped() {
        var sample by mutableStateOf(User("Alpha", 30))
        var parentRecomposeCount = 0
        var childRecomposeCount = 0
        composeTestRule.setContent {
            ParentComposable(
                sample = sample,
                onParentRecompose = remember { { parentRecomposeCount++ } },
                onChildRecompose = remember { { childRecomposeCount++ } },
            )
        }
        childRecomposeCount = 0
        parentRecomposeCount = 0

        sample = sample.copy(age = 31)
        composeTestRule.waitForIdle()

        assertEquals(1, parentRecomposeCount)
        assertEquals(0, childRecomposeCount)

        sample = sample.copy(name = "Beta")
        composeTestRule.waitForIdle()
        assertEquals(1, childRecomposeCount)
    }
}