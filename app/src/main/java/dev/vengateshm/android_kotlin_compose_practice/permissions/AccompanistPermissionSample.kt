package dev.vengateshm.android_kotlin_compose_practice.permissions

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AccompanistPermissionSample(modifier: Modifier = Modifier) {
  val permissionState =
    rememberMultiplePermissionsState(
      permissions =
        listOf(
          Manifest.permission.CAMERA,
          Manifest.permission.RECORD_AUDIO,
        ),
    )

  val lifecycleOwner = LocalLifecycleOwner.current
  DisposableEffect(
    key1 = lifecycleOwner,
    effect = {
      val observer =
        LifecycleEventObserver { _, event ->
          if (event == Lifecycle.Event.ON_START) {
            permissionState.launchMultiplePermissionRequest()
          }
        }
      lifecycleOwner.lifecycle.addObserver(observer)
      onDispose {
        lifecycleOwner.lifecycle.removeObserver(observer)
      }
    },
  )

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    permissionState.permissions.forEach { permissionState ->
      when (permissionState.permission) {
        Manifest.permission.CAMERA -> {
          when {
            permissionState.status.isGranted -> {
              Text(text = "Camera permission accepted")
            }

            permissionState.status.shouldShowRationale -> {
              // Usually it will be a dialog
              Text(text = "Camera permission required to take photos")
            }

            else -> {
              Text(text = "Camera permission was permanently denied. You can enable it in app settings")
            }
          }
        }

        Manifest.permission.RECORD_AUDIO -> {
          when {
            permissionState.status.isGranted -> {
              Text(text = "Record Audio permission accepted")
            }

            permissionState.status.shouldShowRationale -> {
              // Usually it will be a dialog
              Text(text = "Record Audio permission required to record voice")
            }

            else -> {
              Text(text = "Record Audio permission was permanently denied. You can enable it in app settings")
            }
          }
        }
      }
    }
  }
}