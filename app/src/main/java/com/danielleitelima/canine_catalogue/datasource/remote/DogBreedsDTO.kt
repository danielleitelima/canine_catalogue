package com.danielleitelima.canine_catalogue.datasource.remote

class DogBreedsDTO(
    val message: Map<String, List<String>>,
    val status: String,
    val code: Int
)