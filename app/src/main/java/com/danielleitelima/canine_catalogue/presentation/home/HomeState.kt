package com.danielleitelima.canine_catalogue.presentation.home

import com.danielleitelima.canine_catalogue.domain.catalog.model.DogBreed

data class HomeState(
    val dogBreeds: List<DogBreed> = emptyList(),
)