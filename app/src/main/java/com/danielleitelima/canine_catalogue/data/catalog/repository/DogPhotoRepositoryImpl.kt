package com.danielleitelima.canine_catalogue.data.catalog.repository

import android.util.Log
import com.danielleitelima.canine_catalogue.data.catalog.mapper.toModel
import com.danielleitelima.canine_catalogue.datasource.local.datastore.access.DogBreedsCache
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class DogPhotoRepositoryImpl(
    private val dogBreedsCache: DogBreedsCache
): DogPhotoRepository {

    override suspend fun get(id: String): DogPhoto {
        return dogBreedsCache.get().first().items.flatMap {
            it.photos
        }.first { it.id == id }.toModel()
    }

    override suspend fun setIsFavorite(id: String, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        val cache = dogBreedsCache.get().first()

        val dogBreeds = cache.items.map { dogBreed ->
            val photos = dogBreed.photos.map {
                if(it.id == id) {
                    it.copy(isFavorite = isFavorite)
                } else {
                    it
                }
            }

            dogBreed.copy(photos = photos)
        }
        Log.d("DogPhotoRepositoryImpl", "setIsFavorite: $id, $isFavorite")

        dogBreedsCache.set(cache.copy(items = dogBreeds))
    }
}