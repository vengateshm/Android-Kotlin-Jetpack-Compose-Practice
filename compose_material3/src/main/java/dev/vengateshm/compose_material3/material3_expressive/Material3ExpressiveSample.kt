package dev.vengateshm.compose_material3.material3_expressive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Material3ExpressiveSample(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        FilledIconButton(
            onClick = {

            },
            shapes = IconButtonDefaults.shapes(),
        ) {
            Icon(
                Icons.Outlined.Lock,
                contentDescription = "Lock Icon",
            )
        }
        FilledIconButton(
            onClick = {},
            shape = IconButtonDefaults.extraLargeRoundShape,
            modifier = Modifier.size(IconButtonDefaults.extraLargeContainerSize()),
        ) {
            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(IconButtonDefaults.extraLargeIconSize),
            )
        }
        FilledIconButton(
            onClick = {},
            shape = IconButtonDefaults.extraLargeRoundShape,
            modifier = Modifier.size(IconButtonDefaults.smallContainerSize(IconButtonDefaults.IconButtonWidthOption.Wide)),
        ) {
            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite Icon",
            )
        }
        CircularWavyProgressIndicator()
        val shapes = remember {
            listOf(
                MaterialShapes.Circle,
                MaterialShapes.Square,
                MaterialShapes.Slanted,
                MaterialShapes.Arch,
                MaterialShapes.Fan,
                MaterialShapes.Arrow,
                MaterialShapes.SemiCircle,
                MaterialShapes.Oval,
                MaterialShapes.Pill,
                MaterialShapes.Triangle,
                MaterialShapes.Diamond,
                MaterialShapes.Pentagon,
                MaterialShapes.Gem,
            )
        }
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            shapes.forEach {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(shape = it.toShape())
                        .background(Color.Gray),
                )
            }
        }

        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = MaterialShapes.Gem.toShape())
                    .background(Color(0xFFEE2424)),
            )
            Box(
                modifier = Modifier
                    .size(92.dp)
                    .clip(shape = MaterialShapes.Gem.toShape())
                    .background(Color(0xFFEA9797)),
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Level 10")
                Icon(
                    Icons.Outlined.CheckCircle,
                    contentDescription = "Favorite Icon",
                    tint = Color(0xFF0F600F),
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Material3ExpressiveSamplePreview(modifier: Modifier = Modifier) {
    Material3ExpressiveSample()
}