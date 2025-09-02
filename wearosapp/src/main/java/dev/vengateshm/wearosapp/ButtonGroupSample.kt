package dev.vengateshm.wearosapp

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.wear.compose.material3.ButtonGroup
import androidx.wear.compose.material3.FilledIconButton
import androidx.wear.compose.material3.Icon

@Composable
fun ButtonGroupSample(modifier: Modifier = Modifier) {
  val playInteractionSource = remember { MutableInteractionSource() }
  val addToInteractionSource = remember { MutableInteractionSource() }

  ButtonGroup {
    FilledIconButton(
      onClick = { },
      modifier = Modifier
        .weight(0.7f)
        .animateWidth(playInteractionSource),
      interactionSource = playInteractionSource,
    ) {
      Icon(
        imageVector = Icons.Rounded.PlayArrow,
        contentDescription = "play action",
      )
    }
    FilledIconButton(
      onClick = { },
      modifier = Modifier
        .animateWidth(addToInteractionSource),
      interactionSource = addToInteractionSource,
    ) {
      Icon(
        imageVector = Icons.Rounded.AddTask,
        contentDescription = "add to action",
      )
    }
  }
}