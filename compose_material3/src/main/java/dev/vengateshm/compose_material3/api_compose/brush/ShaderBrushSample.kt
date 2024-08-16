package dev.vengateshm.compose_material3.api_compose.brush

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R

@Composable
fun ShaderBrushSample(modifier: Modifier = Modifier) {
    val imageBrush = ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.dog)))
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Text with ShaderBrush",
            modifier = modifier,
            style = TextStyle(
                brush = imageBrush,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Canvas(modifier = Modifier.requiredSize(300.dp)) {
            drawCircle(brush = imageBrush)
        }
    }
}