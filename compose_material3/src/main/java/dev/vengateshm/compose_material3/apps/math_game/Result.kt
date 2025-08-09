package dev.vengateshm.compose_material3.apps.math_game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R

@Composable
fun Result(
  modifier: Modifier = Modifier,
  score: Int,
  onPlayAgainClick: () -> Unit,
  onExitClick: () -> Unit,
) {
  ResultContent(
    score = score,
    onPlayAgainClick = onPlayAgainClick,
    onExitClick = onExitClick,
  )
}

@Composable
fun ResultContent(
  modifier: Modifier = Modifier,
  score: Int,
  onPlayAgainClick: () -> Unit,
  onExitClick: () -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .paint(
        painter = painterResource(R.drawable.bg3),
        contentScale = ContentScale.FillBounds,
      ),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier = Modifier.height(50.dp))
    Text(
      text = "Congratulations!",
      fontSize = 24.sp,
      color = Color.Red,
      fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.height(30.dp))
    Text(
      text = "Your score : $score",
      fontSize = 24.sp,
      color = Color.Red,
    )
    Spacer(modifier = Modifier.height(100.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Button(
        modifier = Modifier.size(width = 170.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color(0XFF54A3A9)),
        onClick = {
          onPlayAgainClick()
        },
      ) {
        Text(
          text = "PLAY AGAIN",
          fontSize = 20.sp,
          color = Color(0XFF54A3A9),
        )
      }
      Button(
        modifier = Modifier.size(width = 170.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color(0XFF54A3A9)),
        onClick = {
          onExitClick()
        },
      ) {
        Text(
          text = "EXIT",
          fontSize = 20.sp,
          color = Color(0XFF54A3A9),
        )
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ResultContentPreview() {
  ResultContent(
    score = 20,
    onPlayAgainClick = {},
    onExitClick = {},
  )
}