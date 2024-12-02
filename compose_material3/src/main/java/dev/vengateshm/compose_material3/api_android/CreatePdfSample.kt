package dev.vengateshm.compose_material3.api_android

import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Build
import android.os.Environment
import android.util.DisplayMetrics
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileOutputStream

@Composable
fun CreatePdfSample(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as? Activity
    val view = LocalView.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Create PDF from text and view")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                activity?.let {
                    val screenWidth: Int
                    val screenHeight: Int

                    val pair = getScreenDimensions(it)
                    screenHeight = pair.first
                    screenWidth = pair.second

                    val pdfDocument = PdfDocument()
                    val pageInfo = PageInfo.Builder(screenWidth, screenHeight, 1).create()
                    val page = pdfDocument.startPage(pageInfo)

                    val paint = Paint()
                    paint.textSize = 50f
                    paint.color = Color.GREEN
                    page.canvas.drawText(
                        "Hello PDF",
                        screenWidth / 2f - 100,
                        screenHeight / 2f,
                        paint,
                    )
                    pdfDocument.finishPage(page)

                    val file = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                        "MyTextPDF.pdf",
                    )
                    pdfDocument.writeTo(FileOutputStream(file))
                    pdfDocument.close()
                }
            },
        ) {
            Text(text = "Create PDF from Text")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                activity?.let {
                    val screenWidth: Int
                    val screenHeight: Int

                    val pair = getScreenDimensions(it)
                    screenHeight = pair.first
                    screenWidth = pair.second

                    view.measure(
                        View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(screenHeight, View.MeasureSpec.EXACTLY),
                    )
                    view.layout(0, 0, screenWidth, screenHeight)

                    val pdfDocument = PdfDocument()
                    val pageInfo = PageInfo.Builder(screenWidth, screenHeight, 1).create()
                    val page = pdfDocument.startPage(pageInfo)

                    view.draw(page.canvas)
                    pdfDocument.finishPage(page)

                    val file = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                        "MyViewPDF.pdf",
                    )
                    pdfDocument.writeTo(FileOutputStream(file))
                    pdfDocument.close()
                }
            },
        ) {
            Text(text = "Create PDF from View")
        }
    }
}

private fun getScreenDimensions(
    activity: Activity,
): Pair<Int, Int> {
    val screenWidth: Int
    val screenHeight: Int
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        screenWidth = activity.windowManager.currentWindowMetrics.bounds.width()
        screenHeight = activity.windowManager.currentWindowMetrics.bounds.height()
    } else {
        val displayMetricsOut = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetricsOut)
        screenWidth = displayMetricsOut.widthPixels
        screenHeight = displayMetricsOut.heightPixels
    }
    return Pair(screenWidth, screenWidth)
}