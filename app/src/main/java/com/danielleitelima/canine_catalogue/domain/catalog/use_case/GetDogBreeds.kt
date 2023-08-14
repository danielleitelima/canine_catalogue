package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import android.util.Log
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class GetDogBreeds(private val dogBreedRepository: DogBreedRepository) {
    operator fun invoke(): Flow<List<DogBreed>> {
        return dogBreedRepository.getAll().onEach {
            Log.d("GetDogBreeds", "getAll update")
        }
    }
}