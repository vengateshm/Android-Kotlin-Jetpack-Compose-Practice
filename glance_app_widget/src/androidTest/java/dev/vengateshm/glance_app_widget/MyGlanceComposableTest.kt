package dev.vengateshm.glance_app_widget

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.semantics.semantics
import androidx.glance.semantics.testTag
import androidx.glance.testing.unit.hasTestTag
import androidx.glance.testing.unit.hasText
import androidx.glance.text.Text
import org.junit.Test

class MyGlanceComposableTest {
    @Test
    fun testTextComposable() = runGlanceAppWidgetUnitTest {
        setAppWidgetSize(size = DpSize(100.dp, 100.dp))

        provideComposable {
            Text(text = "Hello, World!", modifier = GlanceModifier.semantics {
                testTag = "hw"
            })
        }

        onNode(hasText("Hello, World!")).assertExists()
        onNode(hasTestTag("hw")).assertExists()
    }
}