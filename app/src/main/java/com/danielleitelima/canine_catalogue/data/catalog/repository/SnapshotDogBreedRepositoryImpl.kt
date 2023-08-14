package com.danielleitelima.canine_catalogue.data.catalog.repository

import android.util.Log
import com.danielleitelima.canine_catalogue.datasource.local.datastore.access.DogBreedsCache
import com.danielleitelima.canine_catalogue.datasource.local.datastore.model.DogBreeds
import com.danielleitelima.canine_catalogue.datasource.remote.SnapshotDogBreedAPI
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class SnapshotDogBreedRepositoryImpl(
    private val snapshotDogBreedAPI: SnapshotDogBreedAPI,
    private val dogBreedsCache: DogBreedsCache
): DogBreedRepository {
    override suspend fun refresh() {
        val dogBreeds = snapshotDogBreedAPI.getAll().map {
            com.danielleitelima.canine_catalogue.datasource.local.datastore.model.DogBreed(
                name = it.name,
                photos = it.photos.map { url ->
                    com.danielleitelima.canine_catalogue.datasource.local.datastore.model.DogPhotoCache(url = url)
                }
            )
        }

        dogBreedsCache.set(DogBreeds(dogBreeds))
    }

    override fun getAll(): Flow<List<DogBreed>> {
        return dogBreedsCache.get().onEach {
            Log.d("SnapshotDogBreedRepositoryImpl", "getAll update")
        }.map {
            return@map it.items.map { breed ->
                DogBreed(
                    name = breed.name,
                    photos = breed.photos.map { photo ->
                        DogPhoto(
                            id = photo.id,
                            url = photo.url
                        )
                    }
                )
            }
        }
    }

}