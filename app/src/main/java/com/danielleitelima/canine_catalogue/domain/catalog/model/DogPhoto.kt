package com.danielleitelima.canine_catalogue.domain.catalog.model

import java.util.UUID

data class DogPhoto(
    val url: String,
    val id: String = UUID.randomUUID().toString(),
    val isFavorite: Boolean = false,
)