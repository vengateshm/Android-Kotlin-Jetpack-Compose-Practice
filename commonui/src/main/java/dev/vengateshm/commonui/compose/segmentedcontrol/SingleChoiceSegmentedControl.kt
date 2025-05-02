package dev.vengateshm.commonui.compose.segmentedcontrol

import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.commonui.compose.utils.DynamicString
import dev.vengateshm.commonui.compose.utils.UiText
import dev.vengateshm.commonui.compose.utils.toUiText

@Composable
fun SingleChoiceSegmentedControl(
    labels: List<UiText>,
    selectedIndex: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SegmentedButtonColors = SegmentedControlDefaults.segmentedButtonColors(),
    icon: @Composable () -> Unit = {},
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier,
    ) {
        labels.forEachIndexed { index, label ->
            SegmentedButton(
                enabled = enabled,
                selected = index == selectedIndex,
                onClick = { onClick(index) },
                icon = icon,
                colors = colors,
                shape = SegmentedButtonDefaults.itemShape(index = index, count = labels.size),
            ) {
                Text(text = label.asString())
            }
        }
    }
}

@Preview
@Composable
private fun SingleChoiceSegmentedControlPreview() {
    val labels = listOf("Sort".toUiText(), "Filter".toUiText())
    var selectedIndex by remember { mutableIntStateOf(0) }
    SingleChoiceSegmentedControl(
        labels = labels,
        selectedIndex = selectedIndex,
        onClick = { selectedIndex = it },
    )
}