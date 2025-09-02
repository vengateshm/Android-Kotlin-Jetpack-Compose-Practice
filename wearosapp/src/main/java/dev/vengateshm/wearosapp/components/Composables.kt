package dev.vengateshm.wearosapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.SelfImprovement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material3.AppCard
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.FilledIconButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.SwitchButton
import androidx.wear.compose.material3.Text

@Composable
fun IconButtonExample(
  modifier: Modifier = Modifier,
) {
  FilledIconButton(
    onClick = { /* ... */ },
    modifier = modifier,
  ) {
    Icon(
      imageVector = Icons.Rounded.Phone,
      contentDescription = "triggers phone action",
    )
  }
}

@Composable
fun TextExample(modifier: Modifier = Modifier) {
  ListHeader {
    Text(
      modifier = modifier
        .fillMaxWidth(),
      textAlign = TextAlign.Center,
      text = "Wear OS App",
    )
  }
}

@Composable
fun CardExample(
  modifier: Modifier = Modifier,
  iconModifier: Modifier = Modifier,
) {
  AppCard(
    modifier = modifier,
    appImage = {
      Icon(
        imageVector = Icons.AutoMirrored.Rounded.Message,
        contentDescription = "triggers open message action",
        modifier = iconModifier,
      )
    },
    appName = { Text("Messages") },
    time = { Text("12m") },
    title = { Text("Kim Green") },
    onClick = { /* ... */ },
  ) {
    Text("On my way!")
  }
}

@Composable
fun ChipExample(
  modifier: Modifier = Modifier,
) {
  Button(
    modifier = modifier,
    onClick = { /* ... */ },
    icon = {
      Icon(
        imageVector = Icons.Rounded.SelfImprovement,
        contentDescription = "triggers meditation action",
      )
    },
  ) {
    Text(
      text = "5 minute Meditation",
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
    )
  }
}

@Composable
fun SwitchChipExample(modifier: Modifier = Modifier) {
  var checked by remember { mutableStateOf(true) }
  SwitchButton(
    modifier = modifier.fillMaxWidth(),
    label = {
      Text(
        "Sound",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.semantics {
          this.contentDescription = if (checked) "On" else "Off"
        },
      )
    },
    checked = checked,
    onCheckedChange = { checked = it },
    enabled = true,
  )
}