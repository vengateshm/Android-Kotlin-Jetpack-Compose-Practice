package dev.vengateshm.xml_kotlin.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.vengateshm.commonui.base.compose.BaseComposeFragment
import dev.vengateshm.commonui.compose.segmentedcontrol.SingleChoiceSegmentedControl
import dev.vengateshm.commonui.compose.theme.Black
import dev.vengateshm.commonui.compose.utils.toUiText

class SegmentedComposeFragment : BaseComposeFragment() {
    @Composable
    override fun ComposeContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Black),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SingleChoiceSegmentedControlSample()
        }
    }

    @Composable
    fun SingleChoiceSegmentedControlSample() {
        val labels1 = listOf("Sort".toUiText(), "Filter".toUiText())
        var selectedIndex1 by remember { mutableIntStateOf(0) }
        SingleChoiceSegmentedControl(
            labels = labels1,
            selectedIndex = selectedIndex1,
            onClick = { selectedIndex1 = it },
        )

        val labels2 = listOf("One".toUiText(), "Two".toUiText(), "Three".toUiText())
        var selectedIndex2 by remember { mutableIntStateOf(0) }
        SingleChoiceSegmentedControl(
            labels = labels2,
            selectedIndex = selectedIndex2,
            onClick = { selectedIndex2 = it },
        )
    }
}