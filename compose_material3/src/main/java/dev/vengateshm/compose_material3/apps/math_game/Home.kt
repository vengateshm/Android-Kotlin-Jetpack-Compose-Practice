package dev.vengateshm.compose_material3.apps.math_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
  modifier: Modifier = Modifier,
  goToGameScreen: (OperationType) -> Unit,
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = "Math Game",
            fontSize = 20.sp,
          )
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color(0XFF215C21),
          titleContentColor = Color.White,
        ),
      )
    },
  ) {
    HomeContent(
      modifier = Modifier.padding(it),
      onClick = goToGameScreen,
    )
  }
}

@Composable
fun HomeContent(
  modifier: Modifier = Modifier,
  onClick: (OperationType) -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .paint(
        painter = painterResource(R.drawable.bg1),
        contentScale = ContentScale.FillBounds,
      ),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceAround,
  ) {
    OperationType.entries.forEach { operation ->
      Button(
        modifier = Modifier
          .padding(32.dp)
          .fillMaxWidth()
          .height(100.dp),
        onClick = {
          onClick(operation)
        },
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0XFF457123),
          contentColor = Color.White,
        ),
      ) {
        Text(
          text = operation.name.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
          fontSize = 32.sp,
        )
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeContentPreview() {
  HomeContent(onClick = {})
}