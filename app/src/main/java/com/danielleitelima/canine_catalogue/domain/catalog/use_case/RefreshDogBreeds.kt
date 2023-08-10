package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository

class RefreshDogBreeds(private val dogBreedRepository: DogBreedRepository) {

    suspend operator fun invoke() {
        dogBreedRepository.refresh()
    }

}
