package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed


class UpdateDogBreedsWithFavorite {
    operator fun invoke(dogBreeds: List<DogBreed>, favoriteId: String): List<DogBreed> {
        return dogBreeds.map { dogBreed ->
            val newList = dogBreed.photos.map { dogPhotoItem ->
                if (dogPhotoItem.id == favoriteId) {
                    dogPhotoItem.copy(isFavorite = dogPhotoItem.isFavorite.not())
                } else {
                    dogPhotoItem
                }
            }
            dogBreed.copy(photos = newList)
        }
    }
}