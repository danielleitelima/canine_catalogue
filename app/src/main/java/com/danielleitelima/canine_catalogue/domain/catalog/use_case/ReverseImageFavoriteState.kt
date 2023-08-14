package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogPhotoRepository

class ReverseImageFavoriteState(private val dogPhotoRepository: DogPhotoRepository) {

    suspend operator fun invoke(dogPhoto: DogPhoto) {
        dogPhotoRepository.setIsFavorite(dogPhoto.id, dogPhoto.isFavorite.not())
    }

}