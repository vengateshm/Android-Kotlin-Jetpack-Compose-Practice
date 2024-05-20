package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.theme.Material3AppTheme

const val DEFAULT_MAX_LINES = 2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContextualFlowRowSample(modifier: Modifier = Modifier) {

    val programmingLanguages = remember {
        listOf(
            "Kotlin",
            "Java",
            "C",
            "C++",
            "C#",
            "Python",
            "Swift",
            "Erlang",
            "Scala",
            "Haskell",
            "Rust",
            "Go",
        )
    }

    var maxLines by remember {
        mutableIntStateOf(DEFAULT_MAX_LINES)
    }

    ContextualFlowRow(
        itemCount = programmingLanguages.size,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = 3,
        maxLines = maxLines,
        overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
            expandIndicator = {
                TextButton(
                    onClick = { maxLines += 1 },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.surfaceVariant,
                        containerColor = MaterialTheme.colorScheme.onSurface,
                    )
                ) {
                    Text(text = "+${this@expandOrCollapseIndicator.totalItemCount - this@expandOrCollapseIndicator.shownItemCount} more")
                }
            },
            collapseIndicator = {
                TextButton(
                    onClick = { maxLines = DEFAULT_MAX_LINES },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.errorContainer,
                        containerColor = MaterialTheme.colorScheme.error,
                    )
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close icon")
                    Text(text = "Hide")
                }
            }
        )
    ) { index ->
        Button(onClick = { }) {
            Text(text = programmingLanguages[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContextualFlowRowSamplePreview(modifier: Modifier = Modifier) {
    Material3AppTheme {
        ContextualFlowRowSample()
    }
}