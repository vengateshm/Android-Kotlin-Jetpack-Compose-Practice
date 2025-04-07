package dev.vengateshm.commonui.navigation

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import java.io.Serializable

class NavigationArgsImpl(private val bundle: Bundle = Bundle()) : NavigationArguments {
    override fun asBundle() = bundle
    override fun getString(key: String): String? = bundle.getString(key)

    override fun getBoolean(key: String): Boolean = bundle.getBoolean(key)

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        bundle.getBoolean(key, defaultValue)

    override fun getInt(key: String): Int = bundle.getInt(key)

    override fun getInt(key: String, defaultValue: Int): Int = bundle.getInt(key, defaultValue)

    override fun getByte(key: String): Byte = bundle.getByte(key)

    override fun getByte(key: String, defaultValue: Byte): Byte = bundle.getByte(key, defaultValue)

    override fun getChar(key: String): Char? = bundle.getChar(key)

    override fun getChar(key: String, defaultValue: Char): Char = bundle.getChar(key, defaultValue)

    override fun getShort(key: String): Short? = bundle.getShort(key)

    override fun getShort(key: String, defaultValue: Short): Short =
        bundle.getShort(key, defaultValue)

    override fun getFloat(key: String): Float? = bundle.getFloat(key)

    override fun getFloat(key: String, defaultValue: Float): Float =
        bundle.getFloat(key, defaultValue)

    override fun getCharSequence(key: String): CharSequence? = bundle.getCharSequence(key)

    override fun getBundle(key: String): Bundle? = bundle.getBundle(key)

    override fun getSerializable(key: String): Serializable? = bundle.getSerializable(key)

    override fun getByteArray(key: String): ByteArray? = bundle.getByteArray(key)

    override fun getShortArray(key: String): ShortArray? = bundle.getShortArray(key)

    override fun getCharArray(key: String): CharArray? = bundle.getCharArray(key)

    override fun getFloatArray(key: String): FloatArray? = bundle.getFloatArray(key)

    override fun getParcelable(key: String): Parcelable? = bundle.getParcelable(key)

    override fun putString(
        key: String,
        value: String,
    ): NavigationArguments {
        bundle.putString(key, value)
        return this
    }

    override fun putBoolean(
        key: String,
        value: Boolean,
    ): NavigationArguments {
        bundle.putBoolean(key, value)
        return this
    }

    override fun putByte(
        key: String,
        value: Byte,
    ): NavigationArguments {
        bundle.putByte(key, value)
        return this
    }

    override fun putChar(
        key: String,
        value: Char,
    ): NavigationArguments {
        bundle.putChar(key, value)
        return this
    }

    override fun putInt(
        key: String,
        value: Int,
    ): NavigationArguments {
        bundle.putInt(key, value)
        return this
    }

    override fun putShort(
        key: String,
        value: Short,
    ): NavigationArguments {
        bundle.putShort(key, value)
        return this
    }

    override fun putFloat(
        key: String,
        value: Float,
    ): NavigationArguments {
        bundle.putFloat(key, value)
        return this
    }

    override fun putCharSequence(
        key: String,
        value: CharSequence,
    ): NavigationArguments {
        bundle.putCharSequence(key, value)
        return this
    }

    override fun putParcelable(
        key: String,
        value: Parcelable,
    ): NavigationArguments {
        bundle.putParcelable(key, value)
        return this
    }

    override fun putParcelableArray(
        key: String,
        value: Array<Parcelable>,
    ): NavigationArguments {
        bundle.putParcelableArray(key, value)
        return this
    }

    override fun putParcelableArrayList(
        key: String,
        value: ArrayList<out Parcelable>,
    ): NavigationArguments {
        bundle.putParcelableArrayList(key, value)
        return this
    }

    override fun putSparseParcelableArray(
        key: String,
        value: SparseArray<out Parcelable>,
    ): NavigationArguments {
        bundle.putSparseParcelableArray(key, value)
        return this
    }

    override fun putIntegerArrayList(
        key: String,
        value: ArrayList<Int>,
    ): NavigationArguments {
        bundle.putIntegerArrayList(key, value)
        return this
    }

    override fun putStringArrayList(
        key: String,
        value: ArrayList<String>,
    ): NavigationArguments {
        bundle.putStringArrayList(key, value)
        return this
    }

    override fun putSerializable(
        key: String,
        value: Serializable,
    ): NavigationArguments {
        bundle.putSerializable(key, value)
        return this
    }

    override fun putByteArray(
        key: String,
        value: ByteArray,
    ): NavigationArguments {
        bundle.putByteArray(key, value)
        return this
    }

    override fun putShortArray(
        key: String,
        value: ShortArray,
    ): NavigationArguments {
        bundle.putShortArray(key, value)
        return this
    }

    override fun putCharArray(
        key: String,
        value: CharArray,
    ): NavigationArguments {
        bundle.putCharArray(key, value)
        return this
    }

    override fun putFloatArray(
        key: String,
        value: FloatArray,
    ): NavigationArguments {
        bundle.putFloatArray(key, value)
        return this
    }

    override fun putBundle(
        key: String,
        value: Bundle,
    ): NavigationArguments {
        bundle.putBundle(key, value)
        return this
    }
}