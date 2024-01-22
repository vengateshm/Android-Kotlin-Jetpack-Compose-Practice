package dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.android_kotlin_compose_practice.R
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.QuizViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WelcomeScreen(
    viewModel: QuizViewModel,
    onStartClicked: () -> Unit,
) {
    val context = LocalContext.current
    val imageBitmap =
        remember {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.quiz_app_bg,
                options,
            ).asImageBitmap()
        }

    var name by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .drawBehind {
                    drawIntoCanvas {
//                    it.drawImage(imageBitmap, Offset(0f, 0f), Paint())
                        it.drawImageRect(
                            imageBitmap,
                            dstOffset = IntOffset(0, 0),
                            dstSize = IntSize(width = size.width.toInt(), height = size.height.toInt()),
                            paint = Paint(),
                        )
                    }
                },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Quiz App!",
            fontSize = 18.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            onClick = { },
            modifier = Modifier.padding(16.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Welcome", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Please enter your name",
                    fontSize = 14.sp,
                    color = Color(0xFF777777),
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                        viewModel.enteredName = it
                    },
                    label = { Text(text = "Name") },
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onStartClicked()
                    },
                ) {
                    Text(text = "START")
                }
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
}
