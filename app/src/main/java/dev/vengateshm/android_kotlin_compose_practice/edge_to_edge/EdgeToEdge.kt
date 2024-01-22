package dev.vengateshm.android_kotlin_compose_practice.edge_to_edge

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EdgeToEdge() {
    val activity = LocalContext.current as ComponentActivity

    var padSafely by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1000L)
        activity.enableEdgeToEdge(
            SystemBarStyle.light(
                Color.parseColor("#FFF39BA1"),
                Color.parseColor("#FFF82B39"),
            ),
            SystemBarStyle.light(
                Color.parseColor("#FFF39BA1"),
                Color.parseColor("#FFF82B39"),
            ),
        )
        delay(1000L)
        padSafely = true
    }

    Scaffold {
        Column(
            modifier =
                Modifier
                    .padding(top = it.calculateTopPadding())
                    .padding(bottom = it.calculateBottomPadding())
                    .safePadModifier(padSafely)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Header",
                fontSize = 24.sp,
            )
            Spacer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f),
            )
            Text(
                text = "Footer",
                fontSize = 24.sp,
            )
        }
    }
}

fun Modifier.safePadModifier(isSafe: Boolean): Modifier {
    return if (isSafe) {
        this
            .navigationBarsPadding()
            .statusBarsPadding()
    } else {
        this
    }
}
