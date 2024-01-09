package dev.vengateshm.android_kotlin_compose_practice.layout_change_keyboard_open

import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun LoginScreen() {
    val isKeyboardVisible by rememberImeState()

    val upperSectionRatio by animateFloatAsState(
        targetValue = if (isKeyboardVisible) 0f else 0.35f,
        label = "upperSectionRatio_anim",
        animationSpec = tween(500)
    )

    GradientBox(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = !isKeyboardVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.35f),
//                    .fillMaxHeight(if (isKeyboardVisible) 0f else 0.35f),
//                        .fillMaxHeight(upperSectionRatio),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Welcome to Jetpack Compose",
                        color = Color.White,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier.fillMaxSize(
                        fraction = if (isSmallScreenHeight()) 0.05f else 0.1f
                    )
                )
                Text(
                    text = "Login",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5
                )
                Spacer(
                    modifier = Modifier.fillMaxSize(
                        fraction = if (isSmallScreenHeight()) 0.05f else 0.1f
                    )
                )
                TextFieldV1(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = "Username",
                    value = "",
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions(),
                    keyboardActions = KeyboardActions()
                )
                TextFieldV1(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = "Password",
                    value = "",
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions(),
                    keyboardActions = KeyboardActions(),
                    trailingIcon = Icons.Default.Lock
                )
                if (isKeyboardVisible) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults
                            .buttonColors(
                                backgroundColor = Color(0XFF0054D3),
                                contentColor = Color.White
                            )
                    ) {
                        Text(
                            text = "Log In",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp)),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults
                                .buttonColors(
                                    backgroundColor = Color(0XFF0054D3),
                                    contentColor = Color.White
                                )
                        ) {
                            Text(
                                text = "Log In",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(500)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun isSmallScreenHeight(): Boolean {
    val config = LocalConfiguration.current
    return config.screenHeightDp <= 786
}

@Composable
fun rememberImeState(): State<Boolean> {
    val imeState = remember { mutableStateOf(false) }
    val view = LocalView.current

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen =
                ViewCompat.getRootWindowInsets(view)?.isVisible(WindowInsetsCompat.Type.ime())
                    ?: true
            imeState.value = isKeyboardOpen
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    return imeState
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}