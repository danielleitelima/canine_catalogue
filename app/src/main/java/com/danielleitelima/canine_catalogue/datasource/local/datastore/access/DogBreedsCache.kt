package com.danielleitelima.canine_catalogue.datasource.local.datastore.access

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStore
import com.danielleitelima.canine_catalogue.datasource.local.datastore.model.DogBreeds
import kotlinx.coroutines.flow.Flow

class DogBreedsCache(context: Context) : CacheAccess<DogBreeds> {
    companion object {
        private const val FILENAME = "dog_breeds.json"
        val Context.storiesDataStore: DataStore<DogBreeds> by dataStore(
            fileName = FILENAME,
            serializer = jsonSerializer(DogBreeds()),
            corruptionHandler = ReplaceFileCorruptionHandler { DogBreeds() }
        )
    }

    private val dataStore = context.storiesDataStore

    override fun get(): Flow<DogBreeds> {
        return dataStore.data
    }

    override suspend fun clear() {
        dataStore.updateData { _ ->
            DogBreeds()
        }
    }

    override suspend fun set(value: DogBreeds) {
        dataStore.updateData { _ ->
            value
        }
    }

}