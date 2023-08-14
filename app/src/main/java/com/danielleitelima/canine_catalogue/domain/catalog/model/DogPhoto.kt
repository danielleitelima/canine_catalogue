package com.danielleitelima.canine_catalogue.domain.catalog.model

data class DogPhoto(
    val url: String,
    val id: String,
    val isFavorite: Boolean = false,
)