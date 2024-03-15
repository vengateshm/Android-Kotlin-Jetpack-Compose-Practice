package dev.vengateshm.compose_material3.shaders

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.core.view.WindowCompat

private const val COLOR_SHADER_SRC =
    """uniform float2 iResolution;
   half4 main(float2 fragCoord) {
   float2 scaled = fragCoord/iResolution.xy;
   return half4(scaled, 0, 1);
}"""

class AGSLShaderActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val colorShader = RuntimeShader(COLOR_SHADER_SRC)
        val shaderBrush = ShaderBrush(colorShader)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .onSizeChanged { size ->
                            colorShader.setFloatUniform(
                                "iResolution",
                                size.width.toFloat(), size.height.toFloat()
                            )
                        }
                        .graphicsLayer {
                           renderEffect = RenderEffect
                               .createShaderEffect(colorShader)
                               .asComposeRenderEffect()
                        },
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        colorShader.setFloatUniform(
                            "iResolution",
                            size.width, size.height
                        )
                        drawCircle(brush = shaderBrush)
                    }*/
                }
            }
        }
    }
}