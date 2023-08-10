package com.danielleitelima.canine_catalogue.data.catalog.repository

import com.danielleitelima.canine_catalogue.data.catalog.mapper.toModel
import com.danielleitelima.canine_catalogue.datasource.local.dao.DogPhotoDAO
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto
import com.danielleitelima.canine_catalogue.domain.catalog.repository.DogPhotoRepository

class DogPhotoRepositoryImpl(
    private val dogPhotoDAO: DogPhotoDAO
): DogPhotoRepository {
    override suspend fun get(id: String): DogPhoto {
        return dogPhotoDAO.get(id).toModel()
    }

    override suspend fun setIsFavorite(id: String, isFavorite: Boolean) {
        val photo = dogPhotoDAO.get(id)

        dogPhotoDAO.update(photo.copy(isFavorite = isFavorite))
    }
}