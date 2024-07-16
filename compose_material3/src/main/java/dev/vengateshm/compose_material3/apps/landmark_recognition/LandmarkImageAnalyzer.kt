package dev.vengateshm.compose_material3.apps.landmark_recognition

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class LandmarkImageAnalyzer(
    private val landmarkClassifier: LandmarkClassifier,
    private val onResult: (List<Classification>) -> Unit
) : ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(321, 321)
            val results = landmarkClassifier.classify(bitmap, rotationDegrees)
            onResult(results)
            
        }
        frameSkipCounter++
        image.close()
    }
}