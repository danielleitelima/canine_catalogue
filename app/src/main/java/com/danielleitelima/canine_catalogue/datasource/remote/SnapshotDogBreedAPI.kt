package com.danielleitelima.canine_catalogue.datasource.remote

import retrofit2.http.GET

interface SnapshotDogBreedAPI {
    @GET("canine_catalogue.json")
    suspend fun getAll(): List<SnapshotDogBreedDTO>

}