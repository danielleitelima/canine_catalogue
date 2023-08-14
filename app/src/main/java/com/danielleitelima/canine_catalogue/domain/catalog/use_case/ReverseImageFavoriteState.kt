package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogPhotoRepository
import kotlinx.coroutines.flow.onEach

class ReverseImageFavoriteState(private val dogPhotoRepository: DogPhotoRepository) {

    suspend operator fun invoke(dogPhoto: DogPhoto) {
        val id = dogPhoto.id ?: return

        dogPhotoRepository.setIsFavorite(id, dogPhoto.isFavorite.not())
    }

}