package dev.vengateshm.compose_material3.ui_components.otp_input_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OtpScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<OtpViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequesters = List(4) {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.code, keyboardManager) {
        val allNumbersEntered = state.code.none { it == null }
        if (allNumbersEntered) {
            focusRequesters.forEach {
                it.freeFocus()
            }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }

    OtpScreen(
        otpState = state,
        focusRequesters = focusRequesters,
        onAction = { action ->
            when (action) {
                is OtpAction.OnEnterNumber -> {
                    if (action.number != null) {
                        focusRequesters[action.index].freeFocus()
                    }
                }

                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}