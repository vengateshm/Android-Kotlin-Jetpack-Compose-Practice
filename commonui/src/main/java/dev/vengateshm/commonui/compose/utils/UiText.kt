package dev.vengateshm.commonui.compose.utils

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource

sealed class UiText {

    fun asString(context: Context): String {
        return when (this) {
            is Empty -> ""
            is DynamicString -> value
            is PluralResource -> context.resources.getQuantityString(resId, quantity, *args)
            is StringResource -> context.getString(resId, *args)
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is Empty -> ""
            is DynamicString -> value
            is PluralResource -> pluralStringResource(id = resId, count = quantity, *args)
            is StringResource -> stringResource(id = resId, *args)
        }
    }
}

data object Empty : UiText()
data class DynamicString(val value: String) : UiText()
class StringResource(
    @StringRes val resId: Int,
    val args: Array<out Any> = arrayOf(),
) : UiText() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StringResource) return false
        return resId == other.resId && args.contentEquals(other.args)
    }

    override fun hashCode(): Int {
        return 31 * resId + args.contentHashCode()
    }
}

class PluralResource(
    @PluralsRes val resId: Int,
    val quantity: Int,
    val args: Array<out Any> = arrayOf(),
) : UiText() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PluralResource) return false
        return resId == other.resId && quantity == other.quantity && args.contentEquals(other.args)
    }

    override fun hashCode(): Int {
        return 31 * resId + args.contentHashCode()
    }
}

fun String.toUiText() = DynamicString(this)
fun Int.toUiText(vararg args: Any) = StringResource(resId = this, args = args)
fun Int.toUiTextPlural(quantity: Int, vararg args: Any) =
    PluralResource(resId = this, quantity = quantity, args = args)