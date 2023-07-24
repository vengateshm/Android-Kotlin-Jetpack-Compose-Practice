package dev.vengateshm.android_kotlin_compose_practice.datastore_protobufs

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import dev.vengateshm.android_kotlin_compose_practice.ThemeStore
import java.io.InputStream
import java.io.OutputStream

object ThemeStoreSerializer : Serializer<ThemeStore> {
    override val defaultValue: ThemeStore
        get() = ThemeStore.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ThemeStore {
        try {
            return ThemeStore.parseFrom(input)
        } catch (exp: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read protobuf", exp)
        }
    }

    override suspend fun writeTo(t: ThemeStore, output: OutputStream) {
        t.writeTo(output)
    }
}