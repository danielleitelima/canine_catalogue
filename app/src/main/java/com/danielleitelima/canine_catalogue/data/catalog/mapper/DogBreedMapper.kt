package com.danielleitelima.canine_catalogue.data.catalog.mapper

import com.danielleitelima.canine_catalogue.datasource.local.model.DogBreedEntity
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed

fun DogBreed.toEntity() = DogBreedEntity(
    name = name,
)

