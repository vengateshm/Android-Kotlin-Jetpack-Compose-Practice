package dev.vengateshm.compose_material3.apps.math_game

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game(
  modifier: Modifier = Modifier,
  operationType: OperationType,
  onBackClick: () -> Unit,
  goToResultScreen: (Int) -> Unit,
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = operationType.name.lowercase()
              .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            fontSize = 20.sp,
          )
        },
        navigationIcon = {
          IconButton(onClick = { onBackClick() }) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color(0XFF397370),
          titleContentColor = Color.White,
          navigationIconContentColor = Color.White,
        ),
      )
    },
  ) {

    val context = LocalContext.current

    var life by remember { mutableIntStateOf(3) }
    var score by remember { mutableIntStateOf(0) }
    var questionText by remember { mutableStateOf("") }
    var answerText by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(true) }
    var correctAnswer by remember { mutableIntStateOf(0) }
    var remainingTimeText by remember { mutableStateOf("30") }
    var totalTimeInMillis by remember { mutableLongStateOf(30000) }
    val timer = remember {
      object : CountDownTimer(totalTimeInMillis, 1000) {
        override fun onFinish() {
          cancel()
          questionText = "Time's up!"
          answerText = ""
          life -= 1
          isButtonEnabled = false
        }

        override fun onTick(p0: Long) {
          remainingTimeText = String.format(Locale.getDefault(), "%02d", p0 / 1000)
        }

      }.start()
    }

    LaunchedEffect(key1 = "math") {
      val questionList = generateQuestion(operationType = operationType)
      questionText = questionList[0].toString()
      correctAnswer = questionList[1].toString().toInt()
    }

    GameContent(
      modifier = Modifier.padding(it),
      life = life,
      score = score,
      remainingTimeText = remainingTimeText,
      questionText = questionText,
      answerText = answerText,
      onAnswerTextChange = { text -> answerText = text },
      isButtonEnabled = isButtonEnabled,
      onOkButtonClick = {
        timer.cancel()
        if (answerText.isEmpty()) {
          Toast.makeText(
            context,
            "Please enter the answer or click NEXT button",
            Toast.LENGTH_SHORT,
          ).show()
        } else {
          isButtonEnabled = false
          if (answerText.toInt() == correctAnswer) {
            score += 10
            questionText = "Correct!"
            answerText = ""
          } else {
            life -= 1
            questionText = "Wrong!"
            answerText = ""
          }
        }
      },
      onNextButtonClick = {
        timer.cancel()
        timer.start()
        if (life == 0) {
          Toast.makeText(
            context,
            "Game Over",
            Toast.LENGTH_SHORT,
          ).show()
          goToResultScreen(score)
        } else {
          val newQuestion = generateQuestion(operationType)
          questionText = newQuestion[0].toString()
          correctAnswer = newQuestion[1].toString().toInt()
          answerText = ""
          isButtonEnabled = true
        }
      },
    )
  }
}

@Composable
fun GameContent(
  modifier: Modifier = Modifier,
  life: Int,
  score: Int,
  remainingTimeText: String,
  questionText: String,
  answerText: String,
  onAnswerTextChange: (String) -> Unit,
  isButtonEnabled: Boolean,
  onOkButtonClick: () -> Unit,
  onNextButtonClick: () -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .paint(
        painter = painterResource(R.drawable.bg2),
        contentScale = ContentScale.FillBounds,
      ),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Text(text = "Life:", fontSize = 15.sp, color = Color.White)
      Text(text = "$life", fontSize = 15.sp, color = Color.White)
      Text(text = "Score:", fontSize = 15.sp, color = Color.White)
      Text(text = "$score", fontSize = 15.sp, color = Color.White)
      Text(text = "Remaining Time:", fontSize = 15.sp, color = Color.White)
      Text(text = remainingTimeText, fontSize = 15.sp, color = Color.White)
    }
    Spacer(modifier = Modifier.height(20.dp))
    QuestionText(text = questionText)
    Spacer(modifier = Modifier.height(15.dp))
    AnswerField(text = answerText, onAnswerTextChange = onAnswerTextChange)
    Spacer(modifier = Modifier.height(50.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      MathAppButton(
        text = "OK",
        onClick = {
          onOkButtonClick()
        },
        isEnabled = isButtonEnabled,
      )
      MathAppButton(
        text = "NEXT",
        onClick = {
          onNextButtonClick()
        },
        isEnabled = true,
      )
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GameContentPreview() {
  GameContent(
    modifier = Modifier,
    life = 3,
    score = 100,
    remainingTimeText = "29",
    questionText = "63 + 7",
    answerText = "70",
    onAnswerTextChange = {},
    isButtonEnabled = true,
    onOkButtonClick = {},
    onNextButtonClick = {},
  )
}