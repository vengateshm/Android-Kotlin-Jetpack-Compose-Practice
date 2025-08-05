package dev.vengateshm.xml_kotlin.compose.settings

import dev.vengateshm.commonui.compose.utils.UiText

data class SettingsListItem(
  val title: UiText,
  val description: UiText,
  val isVisible: Boolean,
)
