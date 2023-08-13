package com.danielleitelima.canine_catalogue.domain.catalog.model

data class DogPhoto(
    val url: String,
    val id: Long? = null,
    val isFavorite: Boolean = false,
)