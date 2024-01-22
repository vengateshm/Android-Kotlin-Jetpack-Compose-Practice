package dev.vengateshm.android_kotlin_compose_practice.meditation_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.android_kotlin_compose_practice.R
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.AquaBlue
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.Beige1
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.Beige2
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.Beige3
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.BlueViolet1
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.BlueViolet2
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.BlueViolet3
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.ButtonBlue
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.DarkerButtonBlue
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.DeepBlue
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.LightGreen1
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.LightGreen2
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.LightGreen3
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.LightRed
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.OrangeYellow1
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.OrangeYellow2
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.OrangeYellow3
import dev.vengateshm.android_kotlin_compose_practice.meditation_ui.ui.TextWhite
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.standardQuadFromTo

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Box(
        modifier =
            Modifier
                .background(color = DeepBlue)
                .fillMaxSize(),
    ) {
        Column {
            Greeting(name = "Vengatesh")
            ChipSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features =
                    listOf(
                        Feature(
                            title = "Sleep meditation",
                            R.drawable.ic_headphone,
                            BlueViolet1,
                            BlueViolet2,
                            BlueViolet3,
                        ),
                        Feature(
                            title = "Tips for sleeping",
                            R.drawable.ic_videocam,
                            LightGreen1,
                            LightGreen2,
                            LightGreen3,
                        ),
                        Feature(
                            title = "Night island",
                            R.drawable.ic_headphone,
                            OrangeYellow1,
                            OrangeYellow2,
                            OrangeYellow3,
                        ),
                        Feature(
                            title = "Calming sounds",
                            R.drawable.ic_headphone,
                            Beige1,
                            Beige2,
                            Beige3,
                        ),
                    ),
            )
        }
        BottomMenu(
            bottomMenus =
                listOf(
                    BottomMenuContent("Home", R.drawable.ic_home),
                    BottomMenuContent("Meditate", R.drawable.ic_bubble),
                    BottomMenuContent("Sleep", R.drawable.ic_moon),
                    BottomMenuContent("Music", R.drawable.ic_music),
                    BottomMenuContent("Profile", R.drawable.ic_profile),
                ),
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun Greeting(name: String = "Vengatesh") {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Good Morning $name!",
                style = MaterialTheme.typography.h5,
                color = TextWhite,
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1,
                color = TextWhite,
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
fun ChipSection(chips: List<String>) {
    val chipScrollState = rememberScrollState()

    var selectedChipIndex by remember { mutableStateOf(0) }

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .horizontalScroll(
                    state = chipScrollState,
                    enabled = true,
                ),
    ) {
        chips.forEachIndexed { index, chip ->
            Chip(
                chip = chip,
                isSelected = selectedChipIndex == index,
                onChipClick = {
                    selectedChipIndex = index
                },
            )
        }
    }
}

@Composable
fun Chip(
    chip: String,
    isSelected: Boolean,
    onChipClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .clickable(onClick = onChipClick)
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) ButtonBlue else DarkerButtonBlue)
                .padding(16.dp),
    ) {
        Text(
            text = chip,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun CurrentMeditation(color: Color = LightRed) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = color)
                .padding(horizontal = 16.dp, vertical = 22.dp)
                .fillMaxWidth(),
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h6,
                color = TextWhite,
            )
            Text(
                text = "Meditation . 3-10 min",
                style = MaterialTheme.typography.body1,
                color = TextWhite,
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(color = ButtonBlue)
                    .padding(10.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Featured",
            style = MaterialTheme.typography.h5,
            color = TextWhite,
            modifier = Modifier.padding(16.dp),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(features.size) { index ->
                FeatureItem(feature = features[index])
            }
        }
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier =
            Modifier
                .padding(8.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(feature.darkColor),
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath =
            Path().apply {
                moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
                standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
                standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
                standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
                standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath =
            Path().apply {
                moveTo(lightPoint1.x, lightPoint1.y)
                standardQuadFromTo(lightPoint1, lightPoint2)
                standardQuadFromTo(lightPoint2, lightPoint3)
                standardQuadFromTo(lightPoint3, lightPoint4)
                standardQuadFromTo(lightPoint4, lightPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }
        Canvas(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor,
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor,
            )
        }
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(15.dp),
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h5,
                lineHeight = 26.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.TopStart),
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart),
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier
                        .clickable {
                            // Handle the click
                        }
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(10.dp))
                        .background(ButtonBlue)
                        .padding(vertical = 6.dp, horizontal = 15.dp),
            )
        }
    }
}

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)

@Composable
fun BottomMenu(
    bottomMenus: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    defaultSelectedItemIndex: Int = 0,
) {
    var selectedItemIndex by remember {
        mutableStateOf(defaultSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .fillMaxWidth()
                .background(color = DeepBlue)
                .padding(15.dp),
    ) {
        bottomMenus.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor,
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
            Modifier.clickable {
                onItemClick()
            },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (isSelected) activeHighlightColor else Color.Transparent)
                    .padding(10.dp),
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp),
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor,
        )
    }
}

data class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int,
)
