package dev.vengateshm.xml_kotlin.compose.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import dev.vengateshm.commonui.base.compose.BaseComposeFragment
import dev.vengateshm.commonui.compose.theme.CommonUiAppTheme

class SettingsFragment : BaseComposeFragment() {

  private val viewModel: SettingsViewModel by viewModels()

  @Composable
  override fun ComposeContent() {
    SettingsScreen(
      settingsList = viewModel.getSettingsList(),
      onAction = viewModel::handleAction,
      modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
    )
  }

  @Preview(showBackground = true, backgroundColor = 0xff121212)
  @Composable
  private fun ComposeContentPreviewScreen() {
    CommonUiAppTheme {
      ComposeContent()
    }
  }
}