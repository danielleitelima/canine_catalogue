package com.danielleitelima.canine_catalogue.datasource.local.datastore.access

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface CacheAccess<T> {

    fun get(): Flow<T>

    suspend fun set(value: T)

    suspend fun clear()

    suspend fun update(block: (T) -> T) {
        val current = get().first()
        val new = block(current)
        set(new)
    }
}


