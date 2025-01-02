package dev.vengateshm.compose_material3.ui_components.otp_input_field

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val VALID_OTP_CODE = "1234"

class OtpViewModel : ViewModel() {
    private val _state = MutableStateFlow(OtpState())
    val state = _state.asStateFlow()

    fun onAction(action: OtpAction) {
        when (action) {
            is OtpAction.OnChangeFieldFocused -> {
                _state.update {
                    it.copy(
                        focusedIndex = action.index,
                    )
                }
            }

            is OtpAction.OnEnterNumber -> {
                enterNumber(action.number, action.index)
            }

            OtpAction.OnKeyboardBack -> {
                val previousIndex = getPreviousFocusedIndex(state.value.focusedIndex)
                _state.update {
                    it.copy(
                        code = it.code.mapIndexed { index, number ->
                            if (index == previousIndex) {
                                null
                            } else {
                                number
                            }
                        },
                        focusedIndex = previousIndex,
                    )
                }
            }
        }
    }

    fun getPreviousFocusedIndex(currentIndex: Int?): Int? {
        return currentIndex?.minus(1)?.coerceAtLeast(0)
    }

    fun getNextFocusedIndex(code: List<Int?>, currentFocusedIndex: Int?): Int? {
        if (currentFocusedIndex == null) return null
        if (currentFocusedIndex == 3) {
            return currentFocusedIndex
        }
        return firstEmptyFieldAfterCurrentFocusedIndex(
            code = code,
            currentFocusedIndex = currentFocusedIndex,
        )
    }

    private fun firstEmptyFieldAfterCurrentFocusedIndex(
        code: List<Int?>,
        currentFocusedIndex: Int,
    ): Int {
        code.forEachIndexed { index, number ->
            if (index <= currentFocusedIndex) {
                return@forEachIndexed
            }
            if (number == null) {
                return index
            }
        }
        return currentFocusedIndex
    }

    fun enterNumber(number: Int?, index: Int) {
        val newCode = state.value.code.mapIndexed { cIndex, cNumber ->
            if (cIndex == index) {
                number
            } else {
                cNumber
            }
        }
        val wasNumberRemoved = number == null
        _state.update {
            it.copy(
                code = newCode,
                focusedIndex = if (wasNumberRemoved || it.code.getOrNull(index) != null) {
                    it.focusedIndex
                } else {
                    getNextFocusedIndex(code = it.code, currentFocusedIndex = it.focusedIndex)
                },
                isValid = if (newCode.none { it == null }) {
                    newCode.joinToString("") == VALID_OTP_CODE
                } else null,
            )
        }
    }
}