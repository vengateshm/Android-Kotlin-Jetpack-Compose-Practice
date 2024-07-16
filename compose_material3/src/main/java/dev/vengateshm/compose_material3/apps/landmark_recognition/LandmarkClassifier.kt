package dev.vengateshm.compose_material3.apps.landmark_recognition

import android.graphics.Bitmap

interface LandmarkClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}