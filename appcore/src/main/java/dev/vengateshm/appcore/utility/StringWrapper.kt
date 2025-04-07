package dev.vengateshm.appcore.utility

import android.content.Context
import android.view.View
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class StringWrapper {
    /**
     * abstract method to return string representation of StringWrapper in [context]
     */
    abstract fun getFormattedString(context: Context): String

    companion object {
        val EMPTY = BasicString("")

        fun plural(@PluralsRes pluralId: Int, count: Int, vararg arguments: Any) =
            PluralString(pluralId, count, arguments)

        fun single(@StringRes stringId: Int, vararg arguments: Any) =
            SingleString(stringId, arguments)
    }
}

/**
 * This class represents basic string
 */
data class BasicString(val stringValue: String) : StringWrapper() {
    override fun getFormattedString(context: Context): String = stringValue
}

/**
 * This class represent Android single string-resource [stringId] and its possible [arguments]
 */
data class SingleString(
    @StringRes val stringId: Int,
    val arguments: Array<out Any> = arrayOf(),
) : StringWrapper() {
    override fun getFormattedString(context: Context): String =
        if (arguments.isEmpty())
            context.getString(stringId)
        else
            context.getString(stringId, *checkArguments(context, arguments))

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

/**
 * This class represent Android plural string-resource [pluralId], its [count] and its possible [arguments]
 */
data class PluralString(
    @PluralsRes val pluralId: Int,
    val count: Int,
    val arguments: Array<out Any> = arrayOf(),
) : StringWrapper() {
    override fun getFormattedString(context: Context): String =
        if (arguments.isEmpty())
            context.resources.getQuantityString(pluralId, count)
        else
            context.resources.getQuantityString(
                pluralId,
                count,
                *checkArguments(context, arguments),
            )

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

/**
 * Returns this String as a [StringWrapper].
 */
fun String.asStringWrapper(): StringWrapper {
    return if (this.isEmpty()) StringWrapper.EMPTY else BasicString(this)
}

/**
 * Returns a single [StringWrapper] for this ID with the passed [arguments].
 */
fun Int.asSingleString(vararg arguments: Any): SingleString {
    return SingleString(this, arguments)
}

/**
 * Returns a plural [StringWrapper] for this ID with the passed [arguments] with the [count] of the number of objects.
 */
fun Int.asPluralString(count: Int, vararg arguments: Any): PluralString {
    return PluralString(this, count, arguments)
}

/**
 * Returns the [String] value of the [stringWrapper] for the `this` [Context]
 */
@Suppress("unused")
fun Context.getStringValue(stringWrapper: StringWrapper): String =
    stringWrapper.getFormattedString(this)

/**
 * Returns The [String] value of the [stringWrapper] for the [Context] of the view.
 */
@Suppress("unused")
fun View.getStringValue(stringWrapper: StringWrapper): String =
    stringWrapper.getFormattedString(context)

private fun checkArguments(context: Context, arguments: Array<out Any>): Array<out Any> {
    return Array(arguments.size) {
        arguments[it].run {
            (this as? StringWrapper)?.getFormattedString(context) ?: this
        }
    }
}