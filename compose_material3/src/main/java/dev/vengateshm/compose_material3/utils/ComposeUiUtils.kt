package dev.vengateshm.compose_material3.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) {
    this@dpToPx.toPx()
}

@Composable
fun Float.pxToDp() = with(LocalDensity.current) {
    this@pxToDp.toDp()
}