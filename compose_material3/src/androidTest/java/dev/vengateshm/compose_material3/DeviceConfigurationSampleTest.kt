package dev.vengateshm.compose_material3

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
import androidx.compose.ui.test.ForcedSize
import androidx.compose.ui.test.LayoutDirection
import androidx.compose.ui.test.RoundScreen
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vengateshm.compose_material3.testing.device_configuration.DeviceConfigurationSample
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceConfigurationSampleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testFontScale() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DeviceConfigurationOverride(
                        DeviceConfigurationOverride.FontScale(3f)
                    ) {
                        DeviceConfigurationSample()
                    }
                }
            }
        }
    }

    @Test
    fun testRtl() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DeviceConfigurationOverride(
                        DeviceConfigurationOverride.LayoutDirection(LayoutDirection.Rtl)
                    ) {
                        DeviceConfigurationSample()
                    }
                }
            }
        }
    }

    @Test
    fun testDpSize() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DeviceConfigurationOverride(
                        DeviceConfigurationOverride.ForcedSize(DpSize(840.dp, 700.dp))
                    ) {
                        DeviceConfigurationSample()
                    }
                }
            }
        }
    }

    @Test
    fun testRoundScreen() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DeviceConfigurationOverride(
                        DeviceConfigurationOverride.RoundScreen(isScreenRound = true)
                    ) {
                        DeviceConfigurationSample()
                    }
                }
            }
        }
    }
}