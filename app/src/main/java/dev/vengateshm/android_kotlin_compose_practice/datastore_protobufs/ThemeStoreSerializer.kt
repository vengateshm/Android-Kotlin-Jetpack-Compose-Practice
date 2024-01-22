package dev.vengateshm.android_kotlin_compose_practice.datastore_protobufs

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import dev.vengateshm.android_kotlin_compose_practice.ThemeStore
import java.io.InputStream
import java.io.OutputStream

object ThemeStoreSerializer : Serializer<dev.vengateshm.android_kotlin_compose_practice.ThemeStore> {
    override val defaultValue: dev.vengateshm.android_kotlin_compose_practice.ThemeStore
        get() = dev.vengateshm.android_kotlin_compose_practice.ThemeStore.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): dev.vengateshm.android_kotlin_compose_practice.ThemeStore {
        try {
            return dev.vengateshm.android_kotlin_compose_practice.ThemeStore.parseFrom(input)
        } catch (exp: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read protobuf", exp)
        }
    }

    override suspend fun writeTo(
        t: dev.vengateshm.android_kotlin_compose_practice.ThemeStore,
        output: OutputStream,
    ) {
        t.writeTo(output)
    }
}
