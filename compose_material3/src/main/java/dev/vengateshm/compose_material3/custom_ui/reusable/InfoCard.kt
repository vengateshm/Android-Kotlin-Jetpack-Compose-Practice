package dev.vengateshm.compose_material3.custom_ui.reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object InfoCardDefaults {
  @Immutable
  data class Colors(
    val backgroundColor: Color,
    val titleColor: Color,
    val buttonColor: Color,
    val buttonContentColor: Color,
    val amountColor: Color,
    val contentColor: Color,
  )

  @Immutable
  data class Sizes(
    val cornerRadius: Dp,
    val padding: Dp,
  )

  fun colors(
    backgroundColor: Color = Color(0xFF008080),
    titleColor: Color = Color.White,
    buttonColor: Color = Color.White,
    buttonContentColor: Color = backgroundColor,
    amountColor: Color = Color.White,
    contentColor: Color = Color.White,
  ) = Colors(
    backgroundColor,
    titleColor,
    buttonColor,
    buttonContentColor,
    amountColor,
    contentColor,
  )

  fun sizes(
    cornerRadius: Dp = 8.dp,
    padding: Dp = 16.dp,
  ) = Sizes(
    cornerRadius = cornerRadius,
    padding = padding,
  )
}

@Composable
fun InfoCard(
  modifier: Modifier = Modifier,
  titleText: String,
  buttonTitle: String,
  amount: String,
  content: @Composable () -> Unit,
  onClick: () -> Unit,
  colors: InfoCardDefaults.Colors = InfoCardDefaults.colors(),
  sizes: InfoCardDefaults.Sizes = InfoCardDefaults.sizes(),
) {
  Column(
    modifier = modifier
      .clip(RoundedCornerShape(sizes.cornerRadius))
      .background(colors.backgroundColor)
      .padding(sizes.padding),
    verticalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(text = titleText, color = colors.titleColor)
      Surface(
        color = Color.Transparent,
        contentColor = colors.buttonContentColor,
      ) {
        Row(
          modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(colors.buttonColor)
            .clickable { onClick() }
            .padding(horizontal = 12.dp),
        ) {
          Text(text = buttonTitle)
          Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
        }
      }
    }
    Text(
      text = amount,
      style = MaterialTheme.typography.titleLarge,
      color = colors.amountColor,
    )
    Surface(
      color = Color.Transparent,
      contentColor = colors.contentColor,
    ) {
      content()
    }
  }
}

@Composable
fun ProgressIndicator(
  modifier: Modifier = Modifier,
  progress: () -> Float,
  color: Color,
) {
  LinearProgressIndicator(
    modifier = modifier
      .clip(RoundedCornerShape(50)),
    progress = progress,
    trackColor = color.copy(.3f),
    color = color,
    drawStopIndicator = {},
    gapSize = 0.dp,
    strokeCap = StrokeCap.Butt,
  )
}

@Composable
@Preview
private fun PreviewInfoCard() {
  MaterialTheme {
    Column(
      verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
      InfoCard(
        titleText = "Weekly Goal",
        buttonTitle = "Create new goal",
        amount = "N/A",
        content = {
          Text(text = "You haven't created any goals yet")
        },
        onClick = {},
      )
      InfoCard(
        titleText = "Current Steps Count",
        buttonTitle = "Update weekly goal",
        amount = "52.74 k",
        content = {
          Column {
            Text(text = "Your current steps progress")
            ProgressIndicator(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50)),
              progress = { 0.7f },
              color = Color.White,
            )
          }
        },
        onClick = {},
        colors = InfoCardDefaults.colors(
          backgroundColor = Color(0xFFFF952B),
        ),
      )
      InfoCard(
        titleText = "Current Weight",
        buttonTitle = "Create new goal",
        amount = "91.4 kg",
        content = {
          Text(text = "Your haven't created any weight goals yet")
        },
        onClick = {},
        colors = InfoCardDefaults.colors(
          backgroundColor = Color.White,
          titleColor = Color.Black,
          amountColor = Color.Black,
          contentColor = Color.Black,
          buttonColor = Color(0xFF008080),
        ),
      )
      InfoCard(
        titleText = "Weight Goal Progress",
        buttonTitle = "Update weight goal",
        amount = "89.3 kg",
        content = {
          ProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFFF952B),
            progress = { .75f },
          )
        },
        onClick = {},
        colors = InfoCardDefaults.colors(
          backgroundColor = Color(0xFFFFE6CC),
          titleColor = Color.Black,
          amountColor = Color.Black,
          contentColor = Color.Black,
          buttonColor = Color(0xFF008080),
          buttonContentColor = Color.White,
        ),
      )
    }
  }
}