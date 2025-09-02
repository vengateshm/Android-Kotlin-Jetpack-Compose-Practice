package dev.vengateshm.wearosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.EdgeButton
import androidx.wear.compose.material3.EdgeButtonSize
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding
import dev.vengateshm.wearosapp.components.CardExample
import dev.vengateshm.wearosapp.components.ChipExample
import dev.vengateshm.wearosapp.components.IconButtonExample
import dev.vengateshm.wearosapp.components.SwitchChipExample
import dev.vengateshm.wearosapp.components.TextExample
import dev.vengateshm.wearosapp.theme.WearAppTheme

class WearOSMainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      WearApp()
    }
  }
}

@Composable
fun WearApp(modifier: Modifier = Modifier) {
  WearAppTheme {
    AppScaffold {
      val listState = rememberTransformingLazyColumnState()
      val transformationSpec = rememberTransformationSpec()
      ScreenScaffold(
        scrollState = listState,
        contentPadding = rememberResponsiveColumnPadding(
          first = ColumnItemType.IconButton,
          last = ColumnItemType.Button,
        ),
        edgeButton = {
          EdgeButton(
            onClick = { /* ... */ },
            buttonSize = EdgeButtonSize.Medium,
          ) {
            Text("More")
          }
        },
      ) { contentPadding ->
        TransformingLazyColumn(
          state = listState,
          contentPadding = contentPadding,
        ) {
          item {
            IconButtonExample()
          }
          item {
            TextExample(
              modifier =
                Modifier
                  .fillMaxWidth()
                  .transformedHeight(this, transformationSpec),
            )
          }
          item {
            CardExample(
              modifier =
                Modifier
                  .fillMaxWidth()
                  .transformedHeight(this, transformationSpec),
            )
          }
          item {
            ChipExample(
              modifier =
                Modifier
                  .fillMaxWidth()
                  .transformedHeight(this, transformationSpec),
            )
          }
          item {
            SwitchChipExample(
              modifier =
                Modifier
                  .fillMaxWidth()
                  .transformedHeight(this, transformationSpec),
            )
          }
          item {
            ButtonGroupSample()
          }
        }
      }
    }
  }
}

@WearPreviewDevices
@Composable
private fun WearAppPreview() {
  WearApp()
}