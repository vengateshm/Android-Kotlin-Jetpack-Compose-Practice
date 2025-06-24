package dev.vengateshm.compose_material3.custom_ui.month_picker

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.util.Calendar

@Composable
fun MonthPickerSample(modifier: Modifier = Modifier) {
  val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
  val year = Calendar.getInstance().get(Calendar.YEAR)

  var showMonthPicker by remember { mutableStateOf(false) }
  var selectedDate by remember { mutableStateOf("Select") }

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    MonthPicker(
      visible = showMonthPicker,
      currentMonth = currentMonth,
      year = year,
      confirmCallback = { month, year ->
        selectedDate = "$month/$year"
      },
      cancelCallback = {
        showMonthPicker = false
      },
    )
    Text(
      text = selectedDate,
      modifier = Modifier.clickable {
        showMonthPicker = true
      },
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthPicker(
  visible: Boolean,
  currentMonth: Int,
  year: Int,
  confirmCallback: (month: String, year: Int) -> Unit,
  cancelCallback: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val months =
    listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")
  var month by remember { mutableStateOf(months[currentMonth]) }
  var year by remember { mutableIntStateOf(year) }
  val interactionSource = remember { MutableInteractionSource() }
  if (visible) {
    BasicAlertDialog(
      onDismissRequest = {
        cancelCallback()
      },
      properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
      ),
    ) {
      Column(
        modifier = Modifier
          .background(
            color = Color.White,
            shape = RoundedCornerShape(8.dp),
          ),
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Icon(
            imageVector = Icons.Rounded.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier
              .size(32.dp)
              .rotate(90f)
              .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                  year--
                },
              )
              .background(color = Color.Transparent),
          )
          Text(text = "$year")
          Icon(
            imageVector = Icons.Rounded.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier
              .size(32.dp)
              .rotate(270f)
              .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                  year++
                },
              )
              .background(color = Color.Transparent),
          )
        }
        FlowRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp),
          itemVerticalAlignment = Alignment.CenterVertically,
        ) {
          months.forEach {
            Box(
              modifier = Modifier
                .size(60.dp)
                .clickable(
                  interactionSource = interactionSource,
                  indication = null,
                  onClick = {
                    month = it
                  },
                )
                .background(color = Color.Transparent),
              contentAlignment = Alignment.Center,
            ) {
              val animatedSize by animateDpAsState(
                targetValue = if (it == month) 60.dp else 0.dp,
                animationSpec = tween(
                  durationMillis = 500,
                  easing = LinearOutSlowInEasing,
                ),
              )
              Box(
                modifier = Modifier
                  .size(size = animatedSize)
                  .background(
                    color = if (it == month) Color.Blue else Color.Transparent,
                    shape = CircleShape,
                  ),
              )
              Text(
                text = it,
                color = if (it == month) Color.White else Color.Blue,
              )
            }
          }
        }
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(
              end = 16.dp,
              bottom = 32.dp,
            ),
          horizontalArrangement = Arrangement.End,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "Cancel",
            modifier = Modifier.clickable {
              cancelCallback.invoke()
            },
          )
          Spacer(modifier = Modifier.width(16.dp))
          OutlinedButton(
            shape = CircleShape,
            onClick = {
              confirmCallback.invoke(month, year)
              cancelCallback.invoke()
            },
          ) {
            Text(text = "Ok")
          }
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewMonthPickerSample() {
  MaterialTheme {
    MonthPickerSample()
  }
}