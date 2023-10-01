package dev.vengateshm.android_kotlin_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.vengateshm.android_kotlin_compose_practice.ksp_samples_tester.KSPSamplesTester
import dev.vengateshm.android_kotlin_compose_practice.text_apis.AnnotatedStringWithImage
import dev.vengateshm.android_kotlin_compose_practice.utils.requestPostNotificationsPermission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPostNotificationsPermission()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        //AnnotatedStringWithImage()
                        KSPSamplesTester()
                    }
                }
            }
        }
    }
}