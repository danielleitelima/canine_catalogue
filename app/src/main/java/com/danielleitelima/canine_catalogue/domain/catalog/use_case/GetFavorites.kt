package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import android.util.Log
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetFavorites(private val dogBreedRepository: DogBreedRepository) {
    operator fun invoke(): Flow<List<DogBreed>> {
        return dogBreedRepository.getAll().onEach {
            Log.d("GetFavorites", "get app update")
        }.filter {
            it.any { it.photos.any { it.isFavorite } }
        }.map { dogBreeds ->
            dogBreeds.map { dogBreed ->
                dogBreed.copy(photos = dogBreed.photos.filter { it.isFavorite })
            }
        }
    }
}