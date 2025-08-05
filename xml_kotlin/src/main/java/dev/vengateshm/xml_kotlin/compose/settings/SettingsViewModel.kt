package dev.vengateshm.xml_kotlin.compose.settings

import androidx.lifecycle.ViewModel
import dev.vengateshm.commonui.compose.utils.toUiText

class SettingsViewModel : ViewModel() {
  fun getSettingsList(): List<SettingsListItem> {
    return listOf(
      SettingsListItem(
        title = "General".toUiText(),
        description = "App wide settings".toUiText(),
        isVisible = true,
      ),
      SettingsListItem(
        title = "Account".toUiText(),
        description = "Manage your account".toUiText(),
        isVisible = true,
      ),
      SettingsListItem(
        title = "Notifications".toUiText(),
        description = "Customize notifications".toUiText(),
        isVisible = true,
      ),
      SettingsListItem(
        title = "Privacy".toUiText(),
        description = "Control your privacy settings".toUiText(),
        isVisible = true,
      ),
      SettingsListItem(
        title = "Help".toUiText(),
        description = "Get help and support".toUiText(),
        isVisible = true,
      ),
    )
  }

  fun handleAction(action: SettingsListItem) {

  }
}