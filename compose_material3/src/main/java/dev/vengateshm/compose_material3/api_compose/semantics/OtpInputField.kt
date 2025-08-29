package dev.vengateshm.compose_material3.api_compose.semantics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OtpInputFiled(modifier: Modifier = Modifier) {
  val state = rememberTextFieldState()
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    OutlinedTextField(
      modifier = Modifier.semantics {
        contentType = ContentType.SmsOtpCode
      },
      state = state,
      inputTransformation = InputTransformation.maxLength(6),
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
      ),
      lineLimits = TextFieldLineLimits.SingleLine,
    )
  }
}
