package com.danielleitelima.canine_catalogue.presentation.favorite

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed

data class FavoritesState(
    val dogBreeds: List<DogBreed> = emptyList(),
)