package dev.vengateshm.android_kotlin_compose_practice.permissions

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dev.vengateshm.android_kotlin_compose_practice.ui.theme.AndroidKotlinComposePracticeTheme

@ExperimentalPermissionsApi
class PermissionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidKotlinComposePracticeTheme {
                val permissionState =
                    rememberMultiplePermissionsState(
                        permissions =
                            listOf(
                                Manifest.permission.CAMERA,
                                Manifest.permission.RECORD_AUDIO,
                            ),
                    )

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifecycleOwner, effect = {
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
                })

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    permissionState.permissions.forEach { perm ->
                        when (perm.permission) {
                            Manifest.permission.CAMERA -> {
                                when {
                                    perm.hasPermission -> {
                                        Text(text = "Camera permission accepted")
                                    }

                                    perm.shouldShowRationale -> {
                                        // Usually it will be a dialog
                                        Text(text = "Camera permission required to take photos")
                                    }

                                    perm.isPermanentlyDenied() -> {
                                        Text(text = "Camera permission was permanently denied. You can enable it in app settings")
                                    }
                                }
                            }

                            Manifest.permission.RECORD_AUDIO -> {
                                when {
                                    perm.hasPermission -> {
                                        Text(text = "Record Audio permission accepted")
                                    }

                                    perm.shouldShowRationale -> {
                                        // Usually it will be a dialog
                                        Text(text = "Record Audio permission required to record voice")
                                    }

                                    perm.isPermanentlyDenied() -> {
                                        Text(text = "Record Audio permission was permanently denied. You can enable it in app settings")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied(): Boolean = shouldShowRationale.not() && hasPermission.not()
