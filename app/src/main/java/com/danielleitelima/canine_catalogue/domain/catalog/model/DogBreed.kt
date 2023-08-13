package com.danielleitelima.canine_catalogue.domain.catalog.model

data class DogBreed(
    val id: Long? = null,
    val name: String,
    val photos: List<DogPhoto>,
)