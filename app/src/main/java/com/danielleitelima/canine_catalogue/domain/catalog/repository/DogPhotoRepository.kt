package com.danielleitelima.canine_catalogue.domain.catalog.repository

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto

interface DogPhotoRepository {
    suspend fun setIsFavorite(id: String, isFavorite: Boolean)

    suspend fun get(id: String): DogPhoto
}