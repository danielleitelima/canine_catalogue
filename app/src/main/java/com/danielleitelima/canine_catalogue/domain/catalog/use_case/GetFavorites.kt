package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavorites(private val dogBreedRepository: DogBreedRepository) {
    operator fun invoke(): Flow<List<DogBreed>> {
        return dogBreedRepository.getAll().map { dogBreedList ->
            dogBreedList.filter { dogBreed ->
                dogBreed.photos.any { photo -> photo.isFavorite }
            }.map { dogBreed ->
                dogBreed.copy(photos = dogBreed.photos.filter { it.isFavorite })
            }
        }
    }
}
