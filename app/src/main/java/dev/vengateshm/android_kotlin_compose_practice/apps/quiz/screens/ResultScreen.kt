package dev.vengateshm.android_kotlin_compose_practice.apps.quiz.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.android_kotlin_compose_practice.R
import dev.vengateshm.android_kotlin_compose_practice.apps.quiz.QuizViewModel

@Composable
fun ResultScreen(
    viewModel: QuizViewModel,
    onStartAgain: () -> Unit,
) {
    val context = LocalContext.current
    val imageBitmap = remember {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        BitmapFactory.decodeResource(
            context.resources,
            R.drawable.quiz_app_bg,
            options
        ).asImageBitmap()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawIntoCanvas {
//                    it.drawImage(imageBitmap, Offset(0f, 0f), Paint())
                    it.drawImageRect(
                        imageBitmap,
                        dstOffset = IntOffset(0, 0),
                        dstSize = IntSize(width = size.width.toInt(), height = size.height.toInt()),
                        paint = Paint()
                    )
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_trophy
            ),
            contentDescription = "Trophy icon"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hey, Congratulations!", fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = viewModel.enteredName, fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your score is ${viewModel.correctAnswersCount} out of ${viewModel.totalCount()}",
            fontSize = 16.sp,
            color = Color(0xFFBBBBBB)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.reset()
                onStartAgain()
            }) {
            Text(text = "START AGAIN")
        }
    }
}

@Preview
@Composable
fun ResultScreenPreview() {

}