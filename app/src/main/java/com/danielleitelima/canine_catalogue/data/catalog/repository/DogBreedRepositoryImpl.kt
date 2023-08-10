package com.danielleitelima.canine_catalogue.data.catalog.repository

import com.danielleitelima.canine_catalogue.data.catalog.mapper.toEntity
import com.danielleitelima.canine_catalogue.data.catalog.mapper.toModel
import com.danielleitelima.canine_catalogue.datasource.local.dao.DogBreedDAO
import com.danielleitelima.canine_catalogue.datasource.local.dao.DogPhotoDAO
import com.danielleitelima.canine_catalogue.datasource.local.model.DogPhotoEntity
import com.danielleitelima.canine_catalogue.datasource.remote.DogBreedAPI
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DogBreedRepositoryImpl(
    private val dogBreedAPI: DogBreedAPI,
    private val dogBreedDAO: DogBreedDAO,
    private val dogPhotoDAO: DogPhotoDAO
): DogBreedRepository {
    override suspend fun refresh() {
        val dogBreeds = dogBreedAPI.getBreeds().message.keys.map {
            DogBreed(
                name = it,
                photos = dogBreedAPI.getPhotos(it)?.message?.map { url ->
                    DogPhoto(url = url)
                }?: emptyList()
            )
        }

        dogBreedDAO.deleteAll()

        dogBreeds.forEach { dogBreed ->
            val dogBreedId = dogBreedDAO.insert(dogBreed.toEntity()).id
            dogPhotoDAO.insertAll(
                dogBreed.photos.map {
                    DogPhotoEntity(url = it.url, dogBreedId = dogBreedId)
                }
            )
        }

        dogBreedDAO.insertAll(dogBreeds.map { it.toEntity() })
    }

    override fun getAll(): Flow<List<DogBreed>> {
        return dogBreedDAO.getAll().map { dogBreed ->
            dogBreed.map {
                DogBreed(
                    name = it.name,
                    photos = dogPhotoDAO.getByDogBreedId(it.id).map { dogPhotoEntity ->
                        dogPhotoEntity.toModel()
                    }
                )
            }
        }
    }

}