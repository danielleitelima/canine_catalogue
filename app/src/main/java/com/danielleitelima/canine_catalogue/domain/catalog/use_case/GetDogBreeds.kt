package com.danielleitelima.canine_catalogue.domain.catalog.use_case

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDogBreeds(private val dogBreedRepository: DogBreedRepository) {
    operator fun invoke(): Flow<List<DogBreed>> {
//        return dogBreedRepository.getAll()
        return flow {
            val photos = listOf(
                DogPhoto(
                    url = "https://images.dog.ceo/breeds/akita/Akita_hiking_in_Shpella_e_Pellumbasit.jpg"
                ),
                DogPhoto(
                    url = "https://images.dog.ceo/breeds/akita/Akita_hiking_in_Shpella_e_Pellumbasit.jpg"
                ),
                DogPhoto(
                    url = "https://images.dog.ceo/breeds/akita/Akita_hiking_in_Shpella_e_Pellumbasit.jpg"
                ),
                DogPhoto(
                    url = "https://images.dog.ceo/breeds/akita/Akita_hiking_in_Shpella_e_Pellumbasit.jpg"
                ),
                DogPhoto(
                    url = "https://images.dog.ceo/breeds/akita/Akita_hiking_in_Shpella_e_Pellumbasit.jpg"
                ),
            )

            val dogBreeds = listOf(
                DogBreed(
                    name = "Akita",
                    photos = photos
                ),
                DogBreed(
                    name = "Beagle",
                    photos = photos
                ),
                DogBreed(
                    name = "Chow",
                    photos = photos
                ),
                DogBreed(
                    name = "Dalmatian",
                    photos = photos
                ),
                DogBreed(
                    name = "Eskimo",
                    photos = photos
                ),
                DogBreed(
                    name = "Husky",
                    photos = photos
                ),
                DogBreed(
                    name = "Labrador",
                    photos = photos
                ),
                DogBreed(
                    name = "Malamute",
                    photos = photos
                ),
            )

            emit(dogBreeds)
        }
    }
}