package com.danielleitelima.canine_catalogue.data.catalog.mapper

import com.danielleitelima.canine_catalogue.datasource.local.model.DogPhotoEntity
import com.danielleitelima.canine_catalogue.domain.catalog.model.DogPhoto

fun DogPhotoEntity.toModel() = DogPhoto(
    url = url,
    id = id,
    isFavorite = isFavorite
)
