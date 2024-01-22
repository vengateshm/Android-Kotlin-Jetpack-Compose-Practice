package dev.vengateshm.android_kotlin_compose_practice.utils

import android.content.Context
import android.view.View
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class StringWrapper {
    abstract fun getFormattedString(context: Context): String

    companion object {
        val EMPTY = BasicString("")
    }
}

data class BasicString(val stringValue: String) : StringWrapper() {
    override fun getFormattedString(context: Context): String = stringValue
}

data class SingleString(
    @StringRes val stringId: Int,
    val arguments: Array<out Any> = arrayOf(),
) : StringWrapper() {
    override fun getFormattedString(context: Context): String {
        return if (arguments.isEmpty()) {
            context.getString(stringId)
        } else {
            context.getString(stringId, *checkArguments(context, arguments))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SingleString

        if (stringId != other.stringId) return false
        if (!arguments.contentEquals(other.arguments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = stringId
        result = 31 * result + arguments.contentHashCode()
        return result
    }
}

data class PluralString(
    @PluralsRes val pluralId: Int,
    val count: Int,
    val arguments: Array<out Any> = arrayOf(),
) : StringWrapper() {
    override fun getFormattedString(context: Context): String {
        return if (arguments.isEmpty()) {
            context.resources.getQuantityString(pluralId, count)
        } else {
            context.resources.getQuantityString(
                pluralId,
                count,
                *checkArguments(context, arguments),
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PluralString

        if (pluralId != other.pluralId) return false
        if (count != other.count) return false
        if (!arguments.contentEquals(other.arguments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pluralId
        result = 31 * result + count
        result = 31 * result + arguments.contentHashCode()
        return result
    }
}

fun String.asStringWrapper(): StringWrapper {
    return if (this.isEmpty()) StringWrapper.EMPTY else BasicString(this)
}

fun Int.asSingleString(vararg arguments: Any): SingleString {
    return SingleString(stringId = this, arguments)
}

fun Int.asPluralString(
    count: Int,
    vararg arguments: Any,
): PluralString {
    return PluralString(pluralId = this, count, arguments)
}

fun Context.getStringValue(stringWrapper: StringWrapper): String = stringWrapper.getFormattedString(this)

fun View.getStringValue(stringWrapper: StringWrapper): String = stringWrapper.getFormattedString(context)

fun checkArguments(
    context: Context,
    arguments: Array<out Any>,
): Array<out Any> {
    return Array(arguments.size) {
        arguments[it].run {
            (this as? StringWrapper)?.getFormattedString(context) ?: this
        }
    }
}
