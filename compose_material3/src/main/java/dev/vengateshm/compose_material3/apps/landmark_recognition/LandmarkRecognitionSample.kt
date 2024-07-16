package dev.vengateshm.compose_material3.apps.landmark_recognition

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

@Composable
fun LandmarkRecognitionSample(modifier: Modifier = Modifier) {
    var classification by remember {
        mutableStateOf(emptyList<Classification>())
    }

    val context = LocalContext.current
    val landmarkImageAnalyzer = remember {
        LandmarkImageAnalyzer(
            landmarkClassifier = TFLiteLandmarkClassifier(
                context = context.applicationContext,
            ),
            onResult = {
                classification = it
            }
        )
    }
    val controller = remember {
        LifecycleCameraController(context.applicationContext).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context.applicationContext),
                landmarkImageAnalyzer
            )
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LandmarkCameraPreview(
            controller = controller,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            classification.forEach {
                Text(
                    text = it.name, modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}