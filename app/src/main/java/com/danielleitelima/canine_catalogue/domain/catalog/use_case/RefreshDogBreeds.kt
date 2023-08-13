package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import kotlinx.coroutines.delay

class RefreshDogBreeds(private val dogBreedRepository: DogBreedRepository) {

    suspend operator fun invoke() {
//        dogBreedRepository.refresh()
        delay(5000L)
    }

}
