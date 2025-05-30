package dev.vengateshm.compose_material3.accessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.OpenWith
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.api_compose.layout.CircularLayout

@Composable
fun TraversalOrderSample(modifier: Modifier = Modifier) {
    CircularLayout(
        modifier = Modifier
            .semantics {
                isTraversalGroup = true
            }
            .padding(16.dp),
        startAngle = 300f,
    ) {
        repeat(12) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier
                        .semantics {
                            traversalIndex = it.toFloat()
                        },
                    text = "${it + 1}",
                )
            }
        }
    }
}

@Composable
fun AccessibilitySamples(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            // Uses merge descendants in the hood
            modifier = Modifier.clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(Icons.Default.OpenWith, contentDescription = "Open")
            Text("Accessibility in Compose")
        }
        Row(
            modifier = Modifier.clickable(
                onClickLabel = "Open this article",
            ) { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(Icons.AutoMirrored.Default.Article, contentDescription = "Article")
            Text("Accessibility in Compose")
        }
        Row(
            modifier = Modifier
                .combinedClickable(
                    onLongClickLabel = "Bookmark this article",
                    onLongClick = {},
                    onClickLabel = "Open this article",
                    onClick = {},
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(Icons.AutoMirrored.Default.Article, contentDescription = "Article")
            Text("Accessibility in Compose")
        }

        Column(
            modifier = Modifier.semantics(mergeDescendants = true) {},
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Account")
            Row {
                Text("Profile")
                Text("\u25CF", modifier = Modifier.semantics { hideFromAccessibility() })
            }
        }
        FavoriteToggle()
        Error(
            errorText = "Fields cannot be empty",
            modifier = Modifier.semantics {
                error("Please add both email and password")
            },
        )
        var progress by remember { mutableFloatStateOf(0.7f) }
        ProgressInfoBar(
            progress = { progress },
            modifier = Modifier
                .semantics {
                    progressBarRangeInfo = ProgressBarRangeInfo(
                        current = progress,
                        range = 0f..1f,
                    )
                },
        )
        Text(
            text = "You have a new message",
            modifier = Modifier.semantics {
                liveRegion = LiveRegionMode.Polite
            },
        )
        Text(
            text = "Emergency alert incoming",
            modifier = Modifier.semantics {
                liveRegion = LiveRegionMode.Assertive
            },
        )
    }
}

@Composable
fun FavoriteToggle(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .toggleable(
                value = checked,
                onValueChange = {
                    checked = it
                },
            )
            .clearAndSetSemantics {
                stateDescription = if (checked) "Favorited" else "Not Favorited"
                toggleableState = ToggleableState(checked)
                role = Role.Switch
            },
    ) {
        Text("Favorite")
        Icon(Icons.Default.Favorite, contentDescription = null)
    }
}


@Composable
fun Error(
    errorText: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = errorText,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier.padding(16.dp),
    )
}

@Composable
fun ProgressInfoBar(progress: () -> Float, modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
fun ArticleSample(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .safeContentPadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ArticleHeader(
            modifier = Modifier.semantics {
                isTraversalGroup = true
                traversalIndex = 0.0f
            },
            headerText = "Collections and sequences",
        )
        ArticleBody(
            modifier = Modifier.semantics {
                isTraversalGroup = true
                traversalIndex = 0.1f
            },
            bodyText = "Collections and sequences are two types of data structures in Kotlin. " +
                    "They both represent a group of elements, but they have different characteristics " +
                    "and use cases.",
        )
        ArticleHeader(
            modifier = Modifier.semantics {
                isTraversalGroup = true
                traversalIndex = 0.2f
            },
            headerText = "Collections",
        )
        ArticleBody(
            modifier = Modifier.semantics {
                isTraversalGroup = true
                traversalIndex = 0.3f
            },
            bodyText = "Collections are eager, meaning that all their elements are computed at once and stored in memory. This makes them suitable for small to medium-sized datasets where you need to access elements quickly or iterate over them multiple times.",
        )
        ArticleHeader(
            modifier = Modifier.semantics {
                isTraversalGroup = true
                traversalIndex = 0.4f
            },
            headerText = "Sequences",
        )
        ArticleBody(
            modifier = Modifier.semantics {
                isTraversalGroup = true
                traversalIndex = 0.5f
            },
            bodyText = "Sequences are lazy, meaning that their elements are computed on demand as you iterate over them. This makes them suitable for large datasets or infinite streams of data where you don't want to load everything into memory at once.",
        )
    }
}

@Composable
fun ArticleHeader(
    headerText: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.headlineSmall,
) {
    Text(
        text = headerText,
        style = style,
        modifier = modifier.semantics { heading() },
    )
}

@Composable
fun ArticleBody(
    bodyText: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Text(
        text = bodyText,
        style = style,
        modifier = modifier,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InteractiveElementsSamplePreview() {
    AccessibilitySamples()
}

@Composable
fun MilkyWayListSample(modifier: Modifier = Modifier) {
    val milkyWay =
        listOf("Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    LazyColumn(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize()
            .semantics {
                collectionInfo = CollectionInfo(
                    rowCount = milkyWay.count(),
                    columnCount = 1,
                )
            },
    ) {
        milkyWay.forEachIndexed { index, text ->
            item {
                Text(
                    text = text,
                    modifier = Modifier.semantics {
                        collectionItemInfo = CollectionItemInfo(
                            rowIndex = index,
                            0, 0, 0,
                        )
                    },
                )
            }
        }
    }
}