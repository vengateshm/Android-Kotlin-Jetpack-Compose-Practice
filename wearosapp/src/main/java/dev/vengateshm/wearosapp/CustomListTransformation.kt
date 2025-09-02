package dev.vengateshm.wearosapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnItemScrollProgress
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.lazy.TransformationSpec
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewSquare
import com.google.android.horologist.compose.layout.AppScaffold
import dev.vengateshm.wearosapp.theme.WearAppTheme

@Composable
fun CustomListTransformation(modifier: Modifier = Modifier) {
  AppScaffold {
    val listState = rememberTransformingLazyColumnState()
    val spec = rememberTransformationSpec()
    val morphingSpec = object : TransformationSpec by spec {
      override fun GraphicsLayerScope.applyContainerTransformation(
        scrollProgress: TransformingLazyColumnItemScrollProgress,
      ) {
        with(spec) {
          applyContainerTransformation(scrollProgress)
        }
        rotationX = (scrollProgress.topOffsetFraction - 0.5f).coerceIn(0f..1f) * 270f
      }
    }
    ScreenScaffold { contentPadding ->
      TransformingLazyColumn(
        state = listState,
        contentPadding = contentPadding,
      ) {
        items(count = 100) { index ->
          Button(
            onClick = {},
            modifier = Modifier
              .fillMaxWidth()
              .transformedHeight(this, morphingSpec)
              .graphicsLayer {
                with(morphingSpec) {
                  applyContainerTransformation(scrollProgress)
                }
              },
          ) {
            Text(
              text = "Item $index",
              modifier = Modifier
                .graphicsLayer {
                  with(morphingSpec) {
                    applyContentTransformation(scrollProgress)
                  }
                },
            )
          }
        }
      }
    }
  }
}

@WearPreviewDevices
@WearPreviewSquare
@Composable
private fun CustomListTransformationPreview() {
  WearAppTheme {
    CustomListTransformation()
  }
}