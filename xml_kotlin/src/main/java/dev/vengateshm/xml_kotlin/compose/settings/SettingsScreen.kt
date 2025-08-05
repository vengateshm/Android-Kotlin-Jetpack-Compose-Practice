package dev.vengateshm.xml_kotlin.compose.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import dev.vengateshm.commonui.compose.text.BodyText
import dev.vengateshm.commonui.compose.theme.Black
import dev.vengateshm.commonui.compose.theme.CommonUiSpacingMd
import dev.vengateshm.commonui.compose.theme.CommonUiSpacingMmd
import dev.vengateshm.commonui.compose.theme.CommonUiSpacingXmd
import dev.vengateshm.commonui.compose.theme.CommonUiSpacingXs
import dev.vengateshm.commonui.compose.theme.CommonUiSpacingXsmm
import dev.vengateshm.commonui.compose.theme.CommonUiTextMedium
import dev.vengateshm.commonui.compose.theme.MediumTonalGrey

@Composable
fun SettingsScreen(
  settingsList: List<SettingsListItem>,
  onAction: (SettingsListItem) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier) {
    Box(
      modifier = Modifier
        .padding(horizontal = CommonUiSpacingMd, vertical = CommonUiSpacingMd)
        .border(
          width = CommonUiSpacingXsmm,
          color = MediumTonalGrey,
          shape = RoundedCornerShape(CommonUiSpacingXs),
        )
        .clip(shape = RoundedCornerShape(CommonUiSpacingXs)),
    ) {
      Column {
        SettingsList(
          settingsListItem = settingsList,
          onClick = onAction,
        )
      }
    }
  }
}

@Composable
fun SettingsList(
  settingsListItem: List<SettingsListItem>,
  onClick: (SettingsListItem) -> Unit,
) {
  settingsListItem.forEach {
    SettingsListItem(
      settingsListItem = it,
      onClick = onClick,
    )
  }
}

@Composable
fun SettingsListItem(
  settingsListItem: SettingsListItem,
  onClick: (SettingsListItem) -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = CommonUiSpacingMd, vertical = CommonUiSpacingMd)
      .selectable(
        selected = true,
        onClick = {
          onClick(settingsListItem)
        },
      ),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Row(
      modifier = Modifier.weight(1f),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        modifier = Modifier.size(CommonUiSpacingXmd),
        imageVector = Icons.Default.Settings,
        contentDescription = null,
      )
      Column(modifier = Modifier.padding(horizontal = CommonUiSpacingMd)) {
        BodyText(
          text = settingsListItem.title.asString(),
          fontSize = CommonUiTextMedium,
          color = Black,
        )
        BodyText(
          text = settingsListItem.description.asString(),
          fontSize = CommonUiTextMedium,
          color = Black,
        )
      }
    }
    Icon(
      modifier = Modifier.size(CommonUiSpacingMmd),
      imageVector = Icons.Default.ChevronRight,
      contentDescription = null,
    )
  }
}