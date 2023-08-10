package com.danielleitelima.canine_catalogue.domain.catalog.repository

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface DogBreedRepository {

    suspend fun refresh()

    fun getAll(): Flow<List<DogBreed>>

}