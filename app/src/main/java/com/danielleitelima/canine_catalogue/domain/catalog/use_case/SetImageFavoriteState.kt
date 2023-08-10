package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogPhotoRepository

class SetImageFavoriteState(private val dogPhotoRepository: DogPhotoRepository) {

    suspend operator fun invoke(photoId: String, isFavorite: Boolean) {
        dogPhotoRepository.setIsFavorite(photoId, isFavorite)
    }

}