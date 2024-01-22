package dev.vengateshm.android_kotlin_compose_practice.custom_layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable

// Measurebles > Placeables > Layout
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        measurePolicy = { measurables, constraints ->
            val placeables =
                measurables.map {
                    it.measure(constraints)
                }

            val groupedPlaceables = mutableListOf<List<Placeable>>()
            var currentGroup = mutableListOf<Placeable>()
            var currentGroupWidth = 0

            placeables.forEach { placeable ->
                if (currentGroupWidth + placeable.width <= constraints.maxWidth) {
                    // Less than screen width
                    currentGroup.add(placeable)
                    currentGroupWidth += placeable.width
                } else {
                    // Screen width reached
                    // Add the current group to groupedPlaceables object
                    groupedPlaceables.add(currentGroup)
                    // Start new group with 1 placeable
                    currentGroup = mutableListOf(placeable)
                    currentGroupWidth = placeable.width
                }
            }

            if (currentGroup.isNotEmpty()) {
                println("Left over placeables size ${currentGroup.size}")
                groupedPlaceables.add(currentGroup)
            }

            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight,
            ) {
                /*var xPosition = 0
                placeables.forEach { placeable ->
                    // Simple Row
                    placeable.place(
                        x = xPosition,
                        y = 0
                    )
                    xPosition += placeable.width
                }*/
                var yPosition = 0
                groupedPlaceables.forEach { row ->
                    var xPosition = 0
                    row.forEach { placeable ->
                        placeable.place(
                            x = xPosition,
                            y = yPosition,
                        )
                        xPosition += placeable.width
                    }
                    yPosition += row.maxOfOrNull { it.height } ?: 0
                }
            }
        },
        content = content,
    )
}
