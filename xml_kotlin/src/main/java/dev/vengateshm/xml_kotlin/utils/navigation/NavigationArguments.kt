package dev.vengateshm.xml_kotlin.utils.navigation

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import java.io.Serializable

interface NavigationArguments {

    fun asBundle(): Bundle? = null

    fun getString(key: String): String?
    fun getBoolean(key: String): Boolean
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun getInt(key: String): Int
    fun getInt(key: String, defaultValue: Int): Int
    fun getByte(key: String): Byte
    fun getByte(key: String, defaultValue: Byte): Byte
    fun getChar(key: String): Char?
    fun getChar(key: String, defaultValue: Char): Char
    fun getShort(key: String): Short?
    fun getShort(key: String, defaultValue: Short): Short
    fun getFloat(key: String): Float?
    fun getFloat(key: String, defaultValue: Float): Float
    fun getCharSequence(key: String): CharSequence?
    fun getBundle(key: String): Bundle?
    fun getSerializable(key: String): Serializable?
    fun getByteArray(key: String): ByteArray?
    fun getShortArray(key: String): ShortArray?
    fun getCharArray(key: String): CharArray?
    fun getFloatArray(key: String): FloatArray?
    fun getParcelable(key: String): Parcelable?

    fun putString(key: String, value: String): NavigationArguments
    fun putBoolean(key: String, value: Boolean): NavigationArguments
    fun putByte(key: String, value: Byte): NavigationArguments
    fun putChar(key: String, value: Char): NavigationArguments
    fun putInt(key: String, value: Int): NavigationArguments
    fun putShort(key: String, value: Short): NavigationArguments
    fun putFloat(key: String, value: Float): NavigationArguments
    fun putCharSequence(key: String, value: CharSequence): NavigationArguments
    fun putParcelable(key: String, value: Parcelable): NavigationArguments
    fun putParcelableArray(key: String, value: Array<Parcelable>): NavigationArguments
    fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>): NavigationArguments
    fun putSparseParcelableArray(
        key: String,
        value: SparseArray<out Parcelable>,
    ): NavigationArguments

    fun putIntegerArrayList(key: String, value: ArrayList<Int>): NavigationArguments
    fun putStringArrayList(key: String, value: ArrayList<String>): NavigationArguments
    fun putSerializable(key: String, value: Serializable): NavigationArguments
    fun putByteArray(key: String, value: ByteArray): NavigationArguments
    fun putShortArray(key: String, value: ShortArray): NavigationArguments
    fun putCharArray(key: String, value: CharArray): NavigationArguments
    fun putFloatArray(key: String, value: FloatArray): NavigationArguments
    fun putBundle(key: String, value: Bundle): NavigationArguments

    companion object {
        var create: (NavigationArguments.() -> Unit) -> NavigationArguments =
            { block -> NavigationArgsImpl().apply(block) }
    }
}
