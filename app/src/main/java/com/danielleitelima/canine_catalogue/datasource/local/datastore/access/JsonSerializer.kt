package com.danielleitelima.canine_catalogue.datasource.local.datastore.access

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Implementation of [Serializer] that uses the [Json] serialization library to read/write into
 * streams. It also rethrows possible [SerializationException] to [CorruptionException]
 */
inline fun <reified T> jsonSerializer(defValue: T) = object : Serializer<T> {
    override val defaultValue: T
        get() = defValue

    @ExperimentalSerializationApi
    override suspend fun readFrom(input: InputStream): T {
        return try {
            Json.decodeFromStream(input)
        } catch (e: SerializationException) {
            throw CorruptionException("Can't decode json to ${T::class.java.name}", e)
        }
    }

    @ExperimentalSerializationApi
    override suspend fun writeTo(t: T, output: OutputStream) {
        try {
            Json.encodeToStream(t, output)
        } catch (e: SerializationException) {
            throw CorruptionException("Can't encode json from ${T::class.java.name}: $t", e)
        }
    }
}
