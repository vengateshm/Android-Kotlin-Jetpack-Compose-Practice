package dev.vengateshm.compose_material3.ui_components.text_fields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalAutofillManager
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun OutlinedSecureTextFieldSample(modifier: Modifier = Modifier) {
    val passwordState = rememberTextFieldState()
    OutlinedSecureTextField(
        state = passwordState,
        placeholder = { Text("Password") },
        supportingText = {
            Text("Password must be at least 8 characters")
        },
        isError = passwordState.text.length in 1..7,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OutlinedSecureTextFieldSamplePreview() {
    OutlinedSecureTextFieldSample()
}

@Composable
fun SignUpSample(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
) {
    val autofillManager = LocalAutofillManager.current
    val usernameState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier.semantics {
                contentType = ContentType.NewUsername
            },
            state = usernameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Username") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedSecureTextField(
            modifier = Modifier.semantics {
                contentType = ContentType.NewPassword
            },
            state = passwordState,
            placeholder = { Text("Password") },
            trailingIcon = {
                Icon(
                    imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = "Toggle password visibility",
                    modifier = Modifier.clickable {
                        passwordVisible = !passwordVisible
                    },
                )
            },
            textObfuscationMode = if (passwordVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                autofillManager?.commit()
                onSignUpClick()
            },
        ) {
            Text(text = "Sign Up")
        }
    }
}

@Composable
fun LoginSample(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
) {
    val usernameState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier.semantics {
                contentType = ContentType.Username
            },
            state = usernameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Username") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedSecureTextField(
            modifier = Modifier.semantics {
                contentType = ContentType.Password
            },
            state = passwordState,
            placeholder = { Text("Password") },
            trailingIcon = {
                Icon(
                    imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = "Toggle password visibility",
                    modifier = Modifier.clickable {
                        passwordVisible = !passwordVisible
                    },
                )
            },
            textObfuscationMode = if (passwordVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onLoginClick,
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun OtpSample(
    modifier: Modifier = Modifier,
    onOtpVerified: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val textFieldOtpState = rememberTextFieldState()
        val basicTextFieldOtpState = rememberTextFieldState()
        TextField(
            state = textFieldOtpState,
            inputTransformation = InputTransformation.maxLength(6),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
        )
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            state = basicTextFieldOtpState,
            inputTransformation = InputTransformation.maxLength(6),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            decorator = { innerTextField ->
                val otpCode = basicTextFieldOtpState.text.toString()
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(6) { index ->
                        Digit(number = otpCode.getOrElse(index, defaultValue = { ' ' }))
                    }
                }
            },
            modifier = Modifier.semantics {
                contentType = ContentType.SmsOtpCode
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onOtpVerified,
        ) {
            Text(text = "Verify otp")
        }
    }
}

@Composable
fun Digit(
    number: Char,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .border(1.5.dp, Color.Gray, RoundedCornerShape(8.dp))
            .background(Color(0xFFFFB6C1), RoundedCornerShape(8.dp)),
    ) {
        Text(
            text = number.toString(),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun HomeSample(modifier: Modifier = Modifier) {
    var textState = rememberTextFieldState()
    var phoneNumberState = rememberTextFieldState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(1.dp, Color.DarkGray, CircleShape)
                .background(Color.LightGray)
                .padding(16.dp),
        ) {
            BasicText(
                text = textState.text.toString(),
                autoSize = TextAutoSize.StepBased(),
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            state = textState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Description") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            state = phoneNumberState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Phone Number") },
            inputTransformation = InputTransformation.maxLength(10).then {
                if (!asCharSequence().isDigitsOnly()) {
                    revertAllChanges()
                }
            },
            outputTransformation = PhoneNumberTransformation,
        )
    }
}

object PhoneNumberTransformation : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        if (length > 0) insert(0, "(")
        if (length > 4) insert(4, ")")
        if (length > 8) insert(8, "-")
    }
}

@Composable
fun TextFieldsSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "signup",
    ) {
        composable("signup") {
            SignUpSample {
                navController.navigate("login")
            }
        }
        composable("login") {
            LoginSample {
                navController.navigate("otp")
            }
        }
        composable("otp") {
            OtpSample {
                navController.navigate("home")
            }
        }
        composable("home") {
            HomeSample()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeSamplePreview() {
    HomeSample()
}